package ChainOfResponsibility;

public class CashDispenserCOR200 extends CashDispenserCOR {
    public CashDispenserCOR200(CashDispenserCOR nextCashDispenserCOR, int denominationCount, int denominationValue) {
        super(nextCashDispenserCOR, denominationCount, denominationValue);
    }
}
