package ParkingLot.models;

import java.time.Duration;
import java.time.Instant;

import ParkingLot.enums.PaymentStatus;
import ParkingLot.exceptions.ParkingSpotOccupiedException;
import ParkingLot.strategy.BillingStrategy;

public class Ticket {

    String ticketId;
    Spot spot;
    Vehicle vehicle;
    Bill bill;
    Instant entryTime;
    Instant exitTime;
    PaymentStatus paymentStatus;

    public Ticket(Spot spot, Vehicle vehicle) throws ParkingSpotOccupiedException {
        this.spot = spot;
        spot.occupySpot(vehicle);
        this.ticketId = Integer.toString((int) (Math.random() * 10000000));
        this.vehicle = vehicle;
        int randomOffsetHrs = (int) (Math.random() * 10);
        int randomOffsetMins = 1 + (int) (Math.random() * 60);
        this.entryTime = Instant.now().minus(Duration.ofHours(randomOffsetHrs));
        entryTime = entryTime.minus(Duration.ofMinutes(randomOffsetMins));
        this.paymentStatus = PaymentStatus.NOT_PAID;
    }

    public Bill generateBill(BillingStrategy billingStrategy) {
        this.exitTime = Instant.now();
        this.bill = new Bill(entryTime, exitTime, billingStrategy, spot.getHourlyCharges(), spot.getFixedCharges());
        return this.bill;
    }

    public Spot getSpot() {
        return this.spot;
    }

    public Bill getBill() {
        return this.bill;
    }

    public void payBill() {
        if (paymentStatus.equals(PaymentStatus.PAID)) {
            System.out.println("Bill Already Paid");
        } else {
            paymentStatus = PaymentStatus.PAID;
            spot.freeSpot();
        }
    }

    @Override
    public String toString() {
        String str = ticketId + "\n" + bill.toString() + "\n" + spot.toString() + "\n"
                + vehicle.getVehicleType().getDescription() + "\n";
        return str;
    }
}
