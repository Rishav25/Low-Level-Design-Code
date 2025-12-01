package ChainOfResponsibility;

public class CashDispenser {
    CashDispenserCOR cashDispenserCOR500;
    CashDispenserCOR cashDispenserCOR200;
    CashDispenserCOR cashDispenserCOR100;

    public CashDispenser() {
        this.cashDispenserCOR100 = new CashDispenserCOR100(null, 50, 100);
        this.cashDispenserCOR200 = new CashDispenserCOR200(cashDispenserCOR100, 20, 200);
        this.cashDispenserCOR500 = new CashDispenserCOR500(cashDispenserCOR200, 30, 500);
    }

    public boolean canDisburseAmount(int amount) {
        return this.cashDispenserCOR500.canDisburseAmount(amount);
    }

    public void disburseAmount(int amount) {
        this.cashDispenserCOR500.disburseAmount(amount);
    }

    public String getNoteCounts() {
        StringBuilder sb = new StringBuilder();
        CashDispenserCOR curr = this.cashDispenserCOR500;
        while (curr != null) {
            sb.append(curr.getDenominationValue());
            sb.append(" ");
            sb.append(curr.getDenominationCount());
            sb.append(" \n");
            curr = curr.getNextCashDispenserCOR();
        }
        return sb.toString();
    }
}
