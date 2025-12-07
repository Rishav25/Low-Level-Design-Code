package builder;

import java.util.List;

import models.Expense;
import models.Person;
import strategy.SplitStrategy;

public class ExpenseBuilder {
    String description;
    double totalAmount;
    Person paidBy;
    List<Person> peopleInvolved;
    List<Double> splitValues; // for percentage and actual split
    SplitStrategy splitStrategy;

    public ExpenseBuilder() {
    }

    public ExpenseBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public ExpenseBuilder setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public ExpenseBuilder setPaidBy(Person paidBy) {
        this.paidBy = paidBy;
        return this;
    }

    public ExpenseBuilder setPeopleInvolved(List<Person> peopleInvolved) {
        this.peopleInvolved = peopleInvolved;
        return this;
    }

    public ExpenseBuilder setSplitValues(List<Double> splitvalues) {
        this.splitValues = splitvalues;
        return this;
    }

    public ExpenseBuilder setSplitStrategy(SplitStrategy splitStrategy) {
        this.splitStrategy = splitStrategy;
        return this;
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

    public List<Person> getPeolpeInvolved() {
        return this.peopleInvolved;
    }

    public List<Double> getSplitValues() {
        return this.splitValues;
    }

    public SplitStrategy getSplitStrategy() {
        return this.splitStrategy;
    }

    public Expense build() {
        try {
            return new Expense(this);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
