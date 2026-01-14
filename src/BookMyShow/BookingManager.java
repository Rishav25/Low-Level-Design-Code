
import java.util.List;
import java.util.Optional;

import enums.PaymentStatus;
import models.Booking;
import models.Seat;
import models.Show;
import models.User;

public class BookingManager {
    private static volatile BookingManager BookingManagerInstance;
    SeatLockManager seatLockManager;

    private BookingManager(SeatLockManager seatLockManager) {
        this.seatLockManager = seatLockManager;
    }

    public static BookingManager getBookingManagerInstance(SeatLockManager seatLockManager) {
        if (BookingManagerInstance == null) {
            synchronized (BookingManager.class) {
                if (BookingManagerInstance == null) {
                    BookingManagerInstance = new BookingManager(seatLockManager);
                }
            }
        }
        return BookingManagerInstance;
    }

    public Optional<Booking> createBooking(User user, List<Seat> seats, Show show, PaymentStrategy paymentStrategy) {
        // try to lock the seats
        boolean canLockSeats = seatLockManager.lockSeats(seats, show);
        // if canLock the seats
        if (canLockSeats) {
            try {
                // try the payment
                double totalPrice = show.getPricingStrategy().getPrice(seats);
                BookingBuilder bb = new BookingBuilder().setSeats(seats).setShow(show).setUser(user);
                PaymentStatus paymentStatus = paymentStrategy.makePayment(user, totalPrice, seats, show);
                bb.setPaymentStatus(paymentStatus);
                // if success mark the seats as booked
                if (paymentStatus == PaymentStatus.SUCCESS) {
                    seatLockManager.markSeatsAsBooked(seats);
                }
                return Optional.of(bb.build());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // we do this to release the locks of the seats,
                // whether the payment succeeds or it fails
                seatLockManager.unlockSeats(seats, show);
            }
        }
        return Optional.empty();
    }

}