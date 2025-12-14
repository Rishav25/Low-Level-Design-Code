/*
Models -> 
Locker -> LockerId, LockerSize -> ENUM, isOccupied, unlockPin, 
getter
public int depositPackage(), 
private generatePin();

Hub
Map<LockerType, Locker>, Address
add, remove

Address
line1Address, pincode

LockerService -> List<Hub>
getAvailableLockerForPackage(Package p, Hub h)

Package
l, b, h
getter
*/

import ChainOfResponsiblity.LockerFindChainService;
import enums.LockerSize;
import models.Hub;
import models.Locker;
import models.Parcel;

public class Main {
    public static void main(String[] args) {
        LockerFindChainService lockerFindChainService = new LockerFindChainService();
        LockerService lockerService = new LockerService(lockerFindChainService);

        Hub h1 = new Hub("Kondapur Hyderabad");
        Hub h2 = new Hub("Marathalli Bengaluru");

        Locker l1 = new Locker(LockerSize.XS, "727283");
        Locker l2 = new Locker(LockerSize.S, "727284");
        Locker l3 = new Locker(LockerSize.L, "727285");
        Locker l4 = new Locker(LockerSize.M, "727286");
        Locker l5 = new Locker(LockerSize.XS, "727287");
        Locker l6 = new Locker(LockerSize.M, "727288");
        h1.addLocker(l1);
        h1.addLocker(l2);
        h1.addLocker(l3);
        h1.addLocker(l4);
        h1.addLocker(l5);
        h1.addLocker(l6);

        Locker l7 = new Locker(LockerSize.XS, "727289");
        Locker l8 = new Locker(LockerSize.XS, "727282");
        Locker l9 = new Locker(LockerSize.XS, "727281");
        Locker l10 = new Locker(LockerSize.S, "727210");
        Locker l11 = new Locker(LockerSize.M, "727221");
        Locker l12 = new Locker(LockerSize.XS, "727222");
        h2.addLocker(l7);
        h2.addLocker(l8);
        h2.addLocker(l9);
        h2.addLocker(l10);
        h2.addLocker(l11);
        h2.addLocker(l12);

        Parcel p1 = new Parcel(2, 3, 4, "Package 1");
        Parcel p2 = new Parcel(30, 30, 30, "Package 2");
        Parcel p3 = new Parcel(200, 30, 40, "Package 3");
        Parcel p4 = new Parcel(10, 20, 30, "Package 4");
        Parcel p5 = new Parcel(4, 3, 4, "Package 5");

        lockerService.depositParcel(h1, p1);
        lockerService.depositParcel(h1, p2);
        lockerService.depositParcel(h1, p3);

        lockerService.depositParcel(h2, p4);
        lockerService.depositParcel(h2, p5);
    }

}
