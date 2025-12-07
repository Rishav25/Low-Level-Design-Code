package models;

import java.util.List;

import builder.ExpenseBuilder;
import strategy.SplitStrategy;

public class Expense {
    String description;
    double totalAmount;
    Person paidBy;
    List<Person> peopleInvolved;
    List<Double> splitValues; // for percentage and actual split
    SplitStrategy splitStrategy;
    List<Split> splits;

    public Expense(ExpenseBuilder expenseBuilder) {
        this.description = expenseBuilder.getDescription();
        this.totalAmount = expenseBuilder.getTotalAmount();
        this.paidBy = expenseBuilder.getPaidBy();
        this.peopleInvolved = expenseBuilder.getPeolpeInvolved();
        this.splitValues = expenseBuilder.getSplitValues();
        this.splitStrategy = expenseBuilder.getSplitStrategy();
        try {
            this.splits = splitStrategy.splitAmount(totalAmount, paidBy, peopleInvolved, splitValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getDescription() {
        return this.description;
    }

    public double getTotalAmount() {
        return this.totalAmount;
    }

    public Person getPaidBy() {
        return this.paidBy;
    }

    public List<Person> getPeopleInvolved() {
        return this.peopleInvolved;
    }

    public List<Double> getSplitValues() {
        return this.splitValues;
    }

    public SplitStrategy getSplitStrategy() {
        return this.splitStrategy;
    }

    public List<Split> getSplits() {
        return this.splits;
    }
}
