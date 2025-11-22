package ParkingLot;

import ParkingLot.enums.VehicleType;
import ParkingLot.models.Bill;
import ParkingLot.models.EntryGate;
import ParkingLot.models.ExitGate;
import ParkingLot.models.ParkingLot;
import ParkingLot.models.Ticket;
import ParkingLot.models.Vehicle;
import ParkingLot.strategy.BillingStrategy;
import ParkingLot.strategy.FixedBillingStrategy;
import ParkingLot.strategy.HourlyBillingStrategy;
import ParkingLot.strategy.NearestToEntryGateStrategy;
import ParkingLot.strategy.NearestToExitGateStrategy;
import ParkingLot.strategy.SpotSelectionStrategy;

public class Main {
    public static void main(String[] args) {
        EntryGate entryGate = new EntryGate(0, 0);
        BillingStrategy billingStrategy = new FixedBillingStrategy();
        ExitGate exitGate = new ExitGate(100, 100, billingStrategy);

        ParkingLot parkingLot = new ParkingLot(entryGate, exitGate);

        parkingLot.addSpot(23, 44, VehicleType.FOUR_WHEELR, 20, 100);
        parkingLot.addSpot(34, 33, VehicleType.FOUR_WHEELR, 25, 500);
        parkingLot.addSpot(54, 29, VehicleType.FOUR_WHEELR, 23, 600);
        parkingLot.addSpot(55, 32, VehicleType.FOUR_WHEELR, 22, 100);
        parkingLot.addSpot(65, 65, VehicleType.FOUR_WHEELR, 21, 100);
        parkingLot.addSpot(77, 6, VehicleType.FOUR_WHEELR, 27, 500);
        parkingLot.addSpot(99, 50, VehicleType.FOUR_WHEELR, 26, 300);
        parkingLot.addSpot(20, 12, VehicleType.FOUR_WHEELR, 24, 200);
        parkingLot.addSpot(22, 22, VehicleType.FOUR_WHEELR, 23, 100);
        parkingLot.addSpot(4, 3, VehicleType.FOUR_WHEELR, 23, 100);
        parkingLot.addSpot(14, 32, VehicleType.HEAVY_VEHICLES, 23, 800);
        parkingLot.addSpot(22, 54, VehicleType.HEAVY_VEHICLES, 267, 600);
        parkingLot.addSpot(25, 12, VehicleType.HEAVY_VEHICLES, 55, 500);
        parkingLot.addSpot(77, 32, VehicleType.HEAVY_VEHICLES, 28, 400);
        parkingLot.addSpot(85, 12, VehicleType.FOUR_WHEELER_ELECTRIC, 29, 100);
        parkingLot.addSpot(45, 90, VehicleType.FOUR_WHEELER_ELECTRIC, 12, 200);
        parkingLot.addSpot(44, 47, VehicleType.FOUR_WHEELER_ELECTRIC, 22, 1200);
        parkingLot.addSpot(99, 33, VehicleType.FOUR_WHEELER_ELECTRIC, 23, 1100);
        parkingLot.addSpot(92, 43, VehicleType.FOUR_WHEELER_ELECTRIC, 24, 1100);

        Vehicle v1 = new Vehicle(VehicleType.FOUR_WHEELR, "125252");
        Vehicle v2 = new Vehicle(VehicleType.FOUR_WHEELR, "827783");
        Vehicle v3 = new Vehicle(VehicleType.HEAVY_VEHICLES, "99877");
        Vehicle v4 = new Vehicle(VehicleType.FOUR_WHEELR, "2636733");
        Vehicle v5 = new Vehicle(VehicleType.FOUR_WHEELER_ELECTRIC, "6267748");
        Vehicle v6 = new Vehicle(VehicleType.HEAVY_VEHICLES, "9887774");
        Vehicle v7 = new Vehicle(VehicleType.FOUR_WHEELR, "636363");

        SpotSelectionStrategy spotSelectionStrategy = new NearestToEntryGateStrategy();
        Ticket t1 = parkingLot.getTicket(v1, spotSelectionStrategy);
        Ticket t2 = parkingLot.getTicket(v2, spotSelectionStrategy);
        Ticket t3 = parkingLot.getTicket(v3, spotSelectionStrategy);
        Bill b1 = parkingLot.getBill(t1);
        Bill b2 = parkingLot.getBill(t2);
        Bill b3 = parkingLot.getBill(t3);
        parkingLot.payBill(t1);
        parkingLot.payBill(t2);
        parkingLot.payBill(t3);
        System.out.println(t1);
        System.out.println(t2);
        System.out.println(t3);

        billingStrategy = new HourlyBillingStrategy();
        spotSelectionStrategy = new NearestToExitGateStrategy();
        exitGate.setBillingStrategy(billingStrategy);
        Ticket t4 = parkingLot.getTicket(v4, spotSelectionStrategy);
        Ticket t5 = parkingLot.getTicket(v5, spotSelectionStrategy);
        Ticket t6 = parkingLot.getTicket(v6, spotSelectionStrategy);
        Ticket t7 = parkingLot.getTicket(v7, spotSelectionStrategy);
        Bill b4 = parkingLot.getBill(t4);
        Bill b5 = parkingLot.getBill(t5);
        Bill b6 = parkingLot.getBill(t6);
        Bill b7 = parkingLot.getBill(t7);
        parkingLot.payBill(t4);
        parkingLot.payBill(t5);
        parkingLot.payBill(t6);
        parkingLot.payBill(t7);
        System.out.println(t4);
        System.out.println(t5);
        System.out.println(t6);
        System.out.println(t7);

    }
}
