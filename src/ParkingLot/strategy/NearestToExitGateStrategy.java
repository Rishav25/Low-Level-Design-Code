package ParkingLot.strategy;

import java.util.List;

import ParkingLot.enums.VehicleType;
import ParkingLot.models.EntryGate;
import ParkingLot.models.ExitGate;
import ParkingLot.models.Spot;

public class NearestToExitGateStrategy implements SpotSelectionStrategy {
    @Override
    public Spot getSpot(VehicleType vType, List<Spot> spots, EntryGate entryGate, ExitGate exitGate) {
        int mnCartesianDistance = Integer.MAX_VALUE;
        Spot targetSpot = null;
        for (Spot spot : spots) {
            if (spot.getVehicleType().equals(vType) && !spot.isOccupied()) {
                int currDist = Math.abs(spot.getPositionX() - exitGate.getPositionX())
                        + Math.abs(spot.getPositionY() - exitGate.getPositionY());
                if (currDist < mnCartesianDistance) {
                    mnCartesianDistance = currDist;
                    targetSpot = spot;
                }
            }
        }
        return targetSpot;
    }
}
