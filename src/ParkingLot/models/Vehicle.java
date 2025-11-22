package ParkingLot.models;

import ParkingLot.enums.VehicleType;

public class Vehicle {
    VehicleType vehicleType;
    String vehicleNumber;

    public Vehicle(VehicleType vehicleType, String vehicleNumber) {
        this.vehicleType = vehicleType;
        this.vehicleNumber = vehicleNumber;
    }

    public VehicleType getVehicleType() {
        return this.vehicleType;
    }

    public String getVehicleNumber() {
        return this.vehicleNumber;
    }
}
