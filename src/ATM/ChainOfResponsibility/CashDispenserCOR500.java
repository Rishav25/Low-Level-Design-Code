package ChainOfResponsibility;

public class CashDispenserCOR500 extends CashDispenserCOR {
    public CashDispenserCOR500(CashDispenserCOR nextCashDispenserCOR, int denominationCount, int denominationValue) {
        super(nextCashDispenserCOR, denominationCount, denominationValue);
    }

}
