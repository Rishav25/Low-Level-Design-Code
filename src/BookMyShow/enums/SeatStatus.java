package enums;

public enum SeatStatus {
    AVAILABLE("AVAILABLE"),
    LOCKED("LOCKED"),
    BOOKED("BOOKED");

    String message;

    private SeatStatus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}
