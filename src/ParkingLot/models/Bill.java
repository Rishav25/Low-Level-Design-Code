package ParkingLot.models;

import java.time.Instant;

import ParkingLot.strategy.BillingStrategy;
import java.time.Duration;

public class Bill {

    float amount;
    Duration totalTime;
    BillingStrategy billingStrategy;

    public Bill(Instant entryTime, Instant exitTime, BillingStrategy billingStrategy, float hourlyCharges,
            float fixedCharges) {
        this.totalTime = Duration.between(entryTime, exitTime);
        this.billingStrategy = billingStrategy;
        calculateAmount(hourlyCharges, fixedCharges);
    }

    private void calculateAmount(float hourlyCharges, float fixedCharges) {
        this.amount = billingStrategy.calculateAmount(totalTime, hourlyCharges, fixedCharges);
    }

    public float getAmount() {
        return this.amount;
    }

    public Duration getTotalTime() {
        return this.getTotalTime();
    }

    @Override
    public String toString() {
        return "Amount : " + Float.toString(amount) + "\nTime (in minutes) : "
                + Long.toString((long) totalTime.toMinutes());
    }
}
