package ParkingLot.strategy;

import java.time.Duration;

public class FixedBillingStrategy implements BillingStrategy {

    @Override
    public float calculateAmount(Duration tDuration, float hourlyCharges, float fixedCharges) {
        return fixedCharges;
    }
}
