package ChainOfResponsibility;

public class CashDispenserCOR100 extends CashDispenserCOR {
    public CashDispenserCOR100(CashDispenserCOR nextCashDispenserCOR, int denominationCount, int denominationValue) {
        super(nextCashDispenserCOR, denominationCount, denominationValue);
    }
}
