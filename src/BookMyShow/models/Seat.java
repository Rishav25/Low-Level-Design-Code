package models;

import enums.SeatStatus;

public class Seat {
    String id;
    SeatStatus seatStatus;
    long lockedAtTimeStamp;

    public String getId() {
        return this.id;
    }

    public SeatStatus getSeatStatus() {
        return this.seatStatus;
    }

    public void setSeatStatus(SeatStatus seatStatus) {
        this.seatStatus = seatStatus;
    }

    public long getLockedAtTimestamp() {
        return this.lockedAtTimeStamp;
    }

    public void setLockedAtTimestamp(long timestamp) {
        this.lockedAtTimeStamp = timestamp;
    }
}
