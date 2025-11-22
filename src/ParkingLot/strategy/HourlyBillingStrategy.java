package ParkingLot.strategy;

import java.time.Duration;

public class HourlyBillingStrategy implements BillingStrategy {

    @Override
    public float calculateAmount(Duration tDuration, float hourlyCharges, float fixedCharges) {
        float minutes = tDuration.toMinutes();
        float totalAmount = hourlyCharges * (minutes / 60F);
        return totalAmount;
    }
}
