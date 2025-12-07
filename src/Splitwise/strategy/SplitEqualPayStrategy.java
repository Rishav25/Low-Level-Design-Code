package strategy;

import java.util.LinkedList;
import java.util.List;

import exceptions.ExpenseCannotBeAdded;
import models.Person;
import models.Split;

public class SplitEqualPayStrategy implements SplitStrategy {
    @Override
    public List<Split> splitAmount(double totalAmount, Person paidBy, List<Person> peopleInvolved,
            List<Double> splits) throws ExpenseCannotBeAdded {
        List<Split> finalSplits = new LinkedList<>();
        if (peopleInvolved == null)
            throw new ExpenseCannotBeAdded("People involved list cannot be null");
        double splitAmount = totalAmount / (double) peopleInvolved.size();
        for (Person person : peopleInvolved) {
            Split split = new Split(person, splitAmount);
            finalSplits.add(split);
        }
        return finalSplits;
    }
}
