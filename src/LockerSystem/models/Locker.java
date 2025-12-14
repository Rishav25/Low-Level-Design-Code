package models;

import enums.LockerSize;
import exceptions.IncorrectPinException;

public class Locker {
    LockerSize lockerSize;
    String lockerId;
    String pin;
    Parcel p;

    public Locker(LockerSize lockerSize, String lockerId) {
        this.lockerSize = lockerSize;
        this.lockerId = lockerId;
    }

    public boolean hasPackage() {
        return (this.p != null);
    }

    public String depositPackage(Parcel p) {
        this.p = p;
        this.pin = "1234";
        return this.pin;
    }

    private String generatePin() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int random = (int) (Math.random() * 10);
            sb.append(random);
        }
        return sb.toString();
    }

    private boolean validatePin(String pin) {
        return pin.equals(this.pin);
    }

    public String getLockerId() {
        return this.lockerId;
    }

    public LockerSize getLockerSize() {
        return this.lockerSize;
    }

    public Parcel collectPackage(String pin) throws IncorrectPinException {
        if (validatePin(pin)) {
            Parcel parcelInLocker = this.p;
            this.p = null;
            this.pin = null;
            return parcelInLocker;
        } else
            throw new IncorrectPinException("Please enter the correct PIN");
    }

    @Override
    public String toString() {
        String result = lockerSize.getSizeString() + " " + lockerId + " " + pin + " " + p.getParcelName();
        return result;
    }
}
