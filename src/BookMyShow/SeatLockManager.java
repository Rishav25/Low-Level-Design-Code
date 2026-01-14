
import enums.SeatStatus;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import models.Seat;
import models.Show;

public class SeatLockManager {
    final Map<Show, Map<Seat, ReentrantLock>> showSeatMap;
    final ScheduledExecutorService cleanupService;

    public SeatLockManager() {
        this.showSeatMap = new ConcurrentHashMap<>();
        cleanupService = Executors.newSingleThreadScheduledExecutor();
        cleanupService.scheduleAtFixedRate(this::cleanUpFunction, 1, 1, TimeUnit.MINUTES);
    }

    public boolean lockSeats(List<Seat> seats, Show show) {
        List<ReentrantLock> acquiredLocks = new ArrayList<>();
        try {
            showSeatMap.putIfAbsent(show, new ConcurrentHashMap<>());
            Map<Seat, ReentrantLock> seatMap = showSeatMap.get(show);

            Collections.sort(seats, (a, b) -> {
                return a.getId().compareTo(b.getId());
            });
            for (Seat seat : seats) {
                seatMap.putIfAbsent(seat, new ReentrantLock());
                ReentrantLock l = seatMap.get(seat);
                // we try to put a lock on the seats
                // if success we check if the seatStatus is avaialble or not
                if (!l.tryLock(15, TimeUnit.SECONDS)) {
                    internalUnlockSeats(acquiredLocks);
                    return false;
                }
                if (seat.getSeatStatus() != SeatStatus.AVAILABLE) {
                    internalUnlockSeats(acquiredLocks);
                    return false;
                }
                // if both the conditions are fulfilled we add the lock to the acquiredLocks
                // list
                acquiredLocks.add(l);
            }

            long now = System.currentTimeMillis();
            for (Seat seat : seats) {
                // we set the LockedAtTimeStamp which will enable us to run cleanUps
                seat.setSeatStatus(SeatStatus.LOCKED);
                seat.setLockedAtTimestamp(now);
            }
            return true;
        } catch (InterruptedException e) {
            // if there is any error, we unlock all the acquiredLocks
            internalUnlockSeats(acquiredLocks);
            return false;
        }
    }

    private void internalUnlockSeats(List<ReentrantLock> acquiredLocks) {
        for (ReentrantLock l : acquiredLocks) {
            if (l.isHeldByCurrentThread()) {
                l.unlock();
            }
        }
    }

    public void unlockSeats(List<Seat> seats, Show show) {
        showSeatMap.putIfAbsent(show, new ConcurrentHashMap<>());
        Map<Seat, ReentrantLock> seatMap = showSeatMap.get(show);
        Collections.sort(seats, (a, b) -> {
            return a.getId().compareTo(b.getId());
        });
        for (Seat seat : seats) {
            ReentrantLock l = seatMap.get(seat);
            if (l != null && l.isHeldByCurrentThread()) {
                if (seat.getSeatStatus() == SeatStatus.LOCKED) {
                    seat.setSeatStatus(SeatStatus.AVAILABLE);
                    seat.setLockedAtTimestamp(0);
                }
                l.unlock();
            }
        }
    }

    private void cleanUpFunction() {
        // cleanUp does not deal with locks. the locks are locked and released during
        // the bookingTime only
        // in the cleanUp only the SeatStatus.LOCKED things is considered and the seat
        // is marked as available
        long now = System.currentTimeMillis();
        for (Map.Entry<Show, Map<Seat, ReentrantLock>> en : showSeatMap.entrySet()) {
            Map<Seat, ReentrantLock> seatMap = en.getValue();
            for (Map.Entry<Seat, ReentrantLock> seatLock : seatMap.entrySet()) {
                Seat s = seatLock.getKey();

                if (s.getSeatStatus() == SeatStatus.LOCKED
                        && (now - s.getLockedAtTimestamp()) > TimeUnit.MINUTES.toMillis(15)) {
                    s.setSeatStatus(SeatStatus.AVAILABLE);
                    s.setLockedAtTimestamp(0);
                }
            }
        }
    }

    public void markSeatsAsBooked(List<Seat> seats) {
        for (Seat seat : seats) {
            seat.setSeatStatus(SeatStatus.BOOKED);
            seat.setLockedAtTimestamp(0);
        }
    }
}