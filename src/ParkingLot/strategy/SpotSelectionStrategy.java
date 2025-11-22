package ParkingLot.strategy;

import java.util.List;

import ParkingLot.enums.VehicleType;
import ParkingLot.models.EntryGate;
import ParkingLot.models.ExitGate;
import ParkingLot.models.Spot;

public interface SpotSelectionStrategy {
    public Spot getSpot(VehicleType vType, List<Spot> spots, EntryGate entryGate, ExitGate exitGate);
}
