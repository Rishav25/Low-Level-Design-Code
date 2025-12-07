package strategy;

import java.util.List;

import exceptions.ExpenseCannotBeAdded;
import models.Person;
import models.Split;

public interface SplitStrategy {
    public List<Split> splitAmount(double totalAmount, Person paidBy, List<Person> peopleInvolved, List<Double> splits)
            throws ExpenseCannotBeAdded;
}
