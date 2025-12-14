package ChainOfResponsiblity;

import enums.LockerSize;

public class LockerFindChainS extends LockerFindChain {
    public LockerFindChainS(LockerFindChain next, LockerSize lockerSize) {
        super(next, lockerSize);
    }
}
