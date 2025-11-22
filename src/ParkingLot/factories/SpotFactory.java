package ParkingLot.factories;

import ParkingLot.enums.VehicleType;
import ParkingLot.models.Spot;

public class SpotFactory {
    public static SpotFactory SpotFactoryInstance;

    public static SpotFactory getInstance() {
        if (SpotFactoryInstance == null) {
            synchronized (SpotFactory.class) {
                if (SpotFactoryInstance == null) {
                    SpotFactoryInstance = new SpotFactory();
                }
            }
        }
        return SpotFactoryInstance;
    }

    private SpotFactory() {
    }

    public Spot generateSpot(int x, int y, VehicleType vehicleType, float hourlyRate, float fixedRate) {
        return new Spot(x, y, vehicleType, hourlyRate, fixedRate);
    }
}
