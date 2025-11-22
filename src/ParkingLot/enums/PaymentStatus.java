package ParkingLot.enums;

public enum PaymentStatus {

    PAID("Paid"),
    NOT_PAID("Not Paid");

    private final String message;

    private PaymentStatus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}
