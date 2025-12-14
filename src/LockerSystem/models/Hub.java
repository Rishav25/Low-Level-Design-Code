package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import enums.LockerSize;

public class Hub {
    String address;
    Map<LockerSize, List<Locker>> lockers;

    public Hub(String address) {
        this.address = address;
        this.lockers = new HashMap<>();
    }

    public void addLocker(Locker l) {
        lockers.computeIfAbsent(l.getLockerSize(), k -> new ArrayList<>()).add(l);
    }

    public String getAddress() {
        return this.address;
    }

    public List<Locker> getLockersOfOneSize(LockerSize lockerSize) {
        if (lockers.containsKey(lockerSize))
            return lockers.get(lockerSize);
        else
            return null;
    }

    public Locker getFreeLockerOfSize(LockerSize lockerSize) {
        if (lockers.containsKey(lockerSize)) {
            List<Locker> lockerList = lockers.get(lockerSize);
            for (Locker l : lockerList) {
                if (!l.hasPackage())
                    return l;
            }
        }
        return null;
    }
}
