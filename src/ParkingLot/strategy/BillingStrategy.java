package ParkingLot.strategy;

import java.time.Duration;

public interface BillingStrategy {

    public float calculateAmount(Duration tDuration, float hourlyCharges, float fixedCharges);
}
