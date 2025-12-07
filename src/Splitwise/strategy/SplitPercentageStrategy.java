package strategy;

import java.util.LinkedList;
import java.util.List;

import exceptions.ExpenseCannotBeAdded;
import models.Person;
import models.Split;

public class SplitPercentageStrategy implements SplitStrategy {
    @Override
    public List<Split> splitAmount(double totalAmount, Person paidBy, List<Person> peopleInvolved,
            List<Double> splits) throws ExpenseCannotBeAdded {
        List<Split> finalSplits = new LinkedList<>();
        double totalValue = 0.0;
        if (peopleInvolved == null || splits == null)
            throw new ExpenseCannotBeAdded("Please add people invlolved and split percentage list");
        for (int i = 0; i < peopleInvolved.size(); i++) {
            double currAmount = totalAmount * (splits.get(i) / 100.0);
            Split split = new Split(peopleInvolved.get(i), currAmount);
            totalValue += currAmount;
            finalSplits.add(split);
        }
        if (Math.abs(totalValue - totalAmount) > 0.01) {
            throw new ExpenseCannotBeAdded("Please add correct percentage splits");
        }
        return finalSplits;
    }
}
