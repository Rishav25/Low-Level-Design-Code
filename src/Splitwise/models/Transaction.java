package models;

public class Transaction {
    Person paidBy;
    Person paidTo;
    double amount;

    public Transaction(Person paidBy, Person paidTo, double amount) {
        this.paidBy = paidBy;
        this.paidTo = paidTo;
        this.amount = amount;
        adjustBalanceSheet();
    }

    public Person getPaidBy() {
        return this.paidBy;
    }

    public Person getPaidTo() {
        return this.paidTo;
    }

    public double getAmount() {
        return this.amount;
    }

    @Override
    public String toString() {
        String str = paidBy.getName() + " paid " + amount + " to " + paidTo.getName();
        return str;
    }

    private void adjustBalanceSheet() {
        paidBy.getBalanceSheet().adjustAmount(paidTo, amount);
        paidTo.getBalanceSheet().adjustAmount(paidBy, -1.0 * amount);
    }

}
