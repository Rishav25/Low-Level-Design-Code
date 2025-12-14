package ChainOfResponsiblity;

import enums.LockerSize;
import exceptions.LockerNotFoundException;
import models.Hub;
import models.Locker;
import models.Parcel;

public class LockerFindChain {
    LockerFindChain next;
    LockerSize lockerSize;

    public LockerFindChain(LockerFindChain next, LockerSize lockerSize) {
        this.next = next;
        this.lockerSize = lockerSize;
    }

    public Locker getLocker(Hub h, Parcel p) throws LockerNotFoundException {
        LockerFindChain currLockerFinder = this;
        while (currLockerFinder != null) {
            double[] lockerDimensions = currLockerFinder.lockerSize.getDimensions();
            double[] parcelDimensions = p.getSortedDimensions();
            boolean lockerFit = true;
            for (int i = 0; i < 3; i++) {
                if (lockerDimensions[i] < (1.2 * parcelDimensions[i])) {
                    lockerFit = false;
                    break;
                }
            }

            if (lockerFit) {
                Locker freeLocker = h.getFreeLockerOfSize(currLockerFinder.lockerSize);
                if (freeLocker != null)
                    return freeLocker;
                else
                    currLockerFinder = currLockerFinder.next;
            } else
                currLockerFinder = currLockerFinder.next;
        }
        throw new LockerNotFoundException("Locker Not Found");
    }

}
