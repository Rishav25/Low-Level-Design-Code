package ParkingLot.models;

import ParkingLot.strategy.BillingStrategy;

public class ExitGate {
    int positionX;
    int positionY;
    BillingStrategy billingStrategy;

    public ExitGate(int positionX, int positionY, BillingStrategy billingStrategy) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.billingStrategy = billingStrategy;
    }

    public void setBillingStrategy(BillingStrategy billingStrategy) {
        this.billingStrategy = billingStrategy;
    }

    public Bill getBill(Ticket ticket) {
        ticket.generateBill(billingStrategy);
        return ticket.getBill();
    }

    public void payBill(Ticket ticket) {
        ticket.payBill();
    }

    public int getPositionX() {
        return this.positionX;
    }

    public int getPositionY() {
        return this.positionY;
    }
}
