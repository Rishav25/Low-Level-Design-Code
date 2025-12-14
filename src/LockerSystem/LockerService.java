import ChainOfResponsiblity.LockerFindChainService;
import java.util.ArrayList;
import java.util.List;
import models.Hub;
import models.Locker;
import models.Parcel;

public class LockerService {
    LockerFindChainService lockerFindChainService;
    List<Hub> hubList;

    public LockerService(LockerFindChainService lockerFindChainService) {
        this.lockerFindChainService = lockerFindChainService;
        this.hubList = new ArrayList<>();
    }

    public void addHub(Hub h) {
        hubList.add(h);
    }

    private Locker getLocker(Hub h, Parcel p) {
        return this.lockerFindChainService.getLocker(h, p);
    }

    public void depositParcel(Hub h, Parcel p) {
        try {
            Locker l = getLocker(h, p);
            System.out.println("Generated Pin is " + l.depositPackage(p));
            System.out.println(l + " \n ---------------------- \n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(" \n ---------------------- \n");
        }
    }

    public Parcel receiveParcel(Locker l, String pin) {
        try {
            return l.collectPackage(pin);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
