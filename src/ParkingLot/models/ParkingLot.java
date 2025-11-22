package ParkingLot.models;

import java.util.ArrayList;
import java.util.List;

import ParkingLot.enums.VehicleType;
import ParkingLot.exceptions.SpotNotAvailableException;
import ParkingLot.factories.SpotFactory;
import ParkingLot.strategy.SpotSelectionStrategy;

public class ParkingLot {
    EntryGate entryGate;
    ExitGate exitGate;
    List<Spot> spots;

    public ParkingLot(EntryGate entryGate, ExitGate exitGate) {
        this.entryGate = entryGate;
        this.exitGate = exitGate;
        spots = new ArrayList<>();
    }

    public void addSpot(int x, int y, VehicleType vehicleType, float hourlyRate, float fixedRate) {
        Spot newSpot = SpotFactory.getInstance().generateSpot(x, y, vehicleType, hourlyRate, fixedRate);
        spots.add(newSpot);
    }

    public Ticket getTicket(Vehicle v, SpotSelectionStrategy spotSelectionStrategy) {
        Ticket ticket = entryGate.getTicket(v, spotSelectionStrategy, spots, exitGate);
        return ticket;
    }

    public Bill getBill(Ticket t) {
        return exitGate.getBill(t);
    }

    public void payBill(Ticket t) {
        exitGate.payBill(t);
    }

}
