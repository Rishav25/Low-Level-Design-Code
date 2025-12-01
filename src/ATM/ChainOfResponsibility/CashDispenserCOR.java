package ChainOfResponsibility;

public abstract class CashDispenserCOR {

    CashDispenserCOR nextCashDispenserCOR;
    int denominationCount;
    int denominationValue;

    public CashDispenserCOR(CashDispenserCOR nexCashDispenserCOR, int denominationCount, int denominationValue) {
        this.nextCashDispenserCOR = nexCashDispenserCOR;
        this.denominationCount = denominationCount;
        this.denominationValue = denominationValue;
    }

    public int getDenominationCount() {
        return this.denominationCount;
    }

    public void increaseDenominationCount(int noteCount) {
        this.denominationCount += noteCount;
    }

    public void decreaseDenominatonCount(int noteCount) {
        this.denominationCount -= noteCount;
    }

    public CashDispenserCOR getNextCashDispenserCOR() {
        return this.nextCashDispenserCOR;
    }

    public int getDenominationValue() {
        return this.denominationValue;
    }

    public boolean canDisburseAmount(int amount) {
        CashDispenserCOR currDispenser = this;
        do {
            int reqNotes = amount / currDispenser.getDenominationValue();
            int transactedNotes = Math.min(reqNotes, currDispenser.getDenominationCount());
            amount = amount - (transactedNotes * currDispenser.getDenominationValue());
            CashDispenserCOR nextDispenser = currDispenser.getNextCashDispenserCOR();
            currDispenser = nextDispenser;
        } while (currDispenser != null);
        return amount == 0;
    }

    public void disburseAmount(int amount) {
        CashDispenserCOR currDispenser = this;
        do {
            int reqNotes = amount / currDispenser.getDenominationValue();
            int transactedNotes = Math.min(reqNotes, currDispenser.getDenominationCount());
            currDispenser.decreaseDenominatonCount(transactedNotes);
            System.out.println(transactedNotes + " notes of denomination " + currDispenser.getDenominationValue()
                    + " were disbursed");
            amount = amount - (transactedNotes * currDispenser.getDenominationValue());
            CashDispenserCOR nextDispenser = currDispenser.getNextCashDispenserCOR();
            currDispenser = nextDispenser;
        } while (currDispenser != null);
    }

}
