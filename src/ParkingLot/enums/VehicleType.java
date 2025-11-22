package ParkingLot.enums;

public enum VehicleType {
    TWO_WHEELER("Two Wheeler, Bikes and Scooters"),
    TWO_WHEELER_ELECTRIC("Two Wheeler, Electric"),
    FOUR_WHEELR("Four Wheeler, Cars"),
    FOUR_WHEELER_ELECTRIC("Four Wheeler, Electric"),
    HEAVY_VEHICLES("Heavy Vehicles, Trucks and Lorries");

    private final String description;

    private VehicleType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

}
