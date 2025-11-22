package ParkingLot.models;

import ParkingLot.enums.VehicleType;
import ParkingLot.exceptions.ParkingSpotOccupiedException;

public class Spot {

    int positionX;
    int positionY;
    VehicleType vechicleType;
    float hourlyRate;
    float fixedRate;
    Vehicle vehicle;
    boolean isOccupied;

    public Spot(int x, int y, VehicleType vehicleType, float hourlyRate, float fixedRate) {
        this.positionX = x;
        this.positionY = y;
        this.vechicleType = vehicleType;
        this.hourlyRate = hourlyRate;
        this.fixedRate = fixedRate;
        this.isOccupied = false;
    }

    public boolean isOccupied() {
        return this.isOccupied;
    }

    public void occupySpot(Vehicle v) throws ParkingSpotOccupiedException {
        if (this.isOccupied) {
            throw new ParkingSpotOccupiedException("Parking Spot already occupied");
        }
        this.isOccupied = true;
        this.vehicle = v;
    }

    public void freeSpot() {
        this.isOccupied = false;
    }

    public float getHourlyCharges() {
        return this.hourlyRate;
    }

    public float getFixedCharges() {
        return this.fixedRate;
    }

    public VehicleType getVehicleType() {
        return this.vechicleType;
    }

    public int getPositionX() {
        return this.positionX;
    }

    public int getPositionY() {
        return this.positionY;
    }

    @Override
    public String toString() {
        return "Position X : " + positionX + "\nPosition Y : " + positionY + "\nHourly Rate : " + hourlyRate
                + "\nFixed Rate : " + fixedRate;
    }
}
