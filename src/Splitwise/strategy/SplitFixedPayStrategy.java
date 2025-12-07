package strategy;

import java.util.LinkedList;
import java.util.List;

import exceptions.ExpenseCannotBeAdded;
import models.Person;
import models.Split;

public class SplitFixedPayStrategy implements SplitStrategy {
    @Override
    public List<Split> splitAmount(double totalAmount, Person paidBy, List<Person> peopleInvolved,
            List<Double> splits) throws ExpenseCannotBeAdded {
        List<Split> finalSplits = new LinkedList<>();
        double totalValue = 0.0;
        if (peopleInvolved == null || splits == null)
            throw new ExpenseCannotBeAdded("Please add people invlolved and split value list");
        for (int i = 0; i < peopleInvolved.size(); i++) {
            Split split = new Split(peopleInvolved.get(i), splits.get(i));
            totalValue += splits.get(i);
            finalSplits.add(split);
        }
        if (Math.abs(totalValue - totalAmount) > 0.01) {
            throw new ExpenseCannotBeAdded("Split Value does not match the total amount");
        }
        return finalSplits;
    }

}
