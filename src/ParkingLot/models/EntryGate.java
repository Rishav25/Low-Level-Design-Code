package ParkingLot.models;

import java.util.List;

import ParkingLot.exceptions.SpotNotAvailableException;
import ParkingLot.strategy.SpotSelectionStrategy;

public class EntryGate {
    int positionX;
    int positionY;

    public EntryGate(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public Ticket getTicket(Vehicle vehicle, SpotSelectionStrategy spotSelectionStrategy, List<Spot> spots,
            ExitGate exitGate)
            throws SpotNotAvailableException {
        synchronized (this) {
            Spot spot = spotSelectionStrategy.getSpot(vehicle.getVehicleType(), spots, this, exitGate);
            if (spot == null) {
                throw new SpotNotAvailableException("Spot is not available. Please Wait");
            }
            Ticket ticket = new Ticket(spot, vehicle);
            return ticket;
        }
    }

    public int getPositionX() {
        return this.positionX;
    }

    public int getPositionY() {
        return this.positionY;
    }
}
