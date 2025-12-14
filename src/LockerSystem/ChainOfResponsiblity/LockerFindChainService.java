package ChainOfResponsiblity;

import enums.LockerSize;
import models.Hub;
import models.Locker;
import models.Parcel;

public class LockerFindChainService {
    LockerFindChain lockerFindChain;

    public LockerFindChainService() {
        LockerFindChain lLocker = new LockerFindChain(null, LockerSize.L);
        LockerFindChain mLocker = new LockerFindChain(lLocker, LockerSize.M);
        LockerFindChain sLocker = new LockerFindChain(mLocker, LockerSize.S);
        LockerFindChain xsLocker = new LockerFindChain(sLocker, LockerSize.XS);
        this.lockerFindChain = xsLocker;
    }

    public Locker getLocker(Hub h, Parcel p) {
        try {
            Locker l = lockerFindChain.getLocker(h, p);
            return l;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}