import builder.ExpenseBuilder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.Expense;
import models.Group;
import models.Person;
import models.Split;
import models.Transaction;

public class Splitwise {
    Map<String, Person> personMap;
    Map<String, Group> groupMap;
    static volatile Splitwise splitWiseInstance;

    public static synchronized Splitwise getSplitwiseInstance() {
        if (splitWiseInstance == null) {
            splitWiseInstance = new Splitwise();
        }
        return splitWiseInstance;
    }

    private Splitwise() {
        personMap = new HashMap<>();
        groupMap = new HashMap<>();
    }

    public Person addPerson(String name) {
        Person p = new Person(name);
        personMap.put(p.getId(), p);
        return p;
    }

    public Group addGroup(String name) {
        Group g = new Group(name);
        groupMap.put(g.getGroupId(), g);
        return g;
    }

    public void createExpense(ExpenseBuilder expenseBuilder) {
        try {
            Expense exp = expenseBuilder.build();
            Person paidBy = exp.getPaidBy();
            List<Split> splits = exp.getSplits();

            for (Split split : splits) {
                Person currPerson = split.getPerson();
                if (!currPerson.equals(paidBy)) {
                    currPerson.getBalanceSheet().adjustAmount(paidBy, -1.0 * split.getAmount());
                    paidBy.getBalanceSheet().adjustAmount(currPerson, split.getAmount());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addPersonToGroup(Group g, Person p) {
        g.addPerson(p);
    }

    public void removePersonFromGroup(Group g, Person p) {
        g.removePerson(p);
    }

    public void addGroupExpense(Group g, ExpenseBuilder expenseBuilder) {
        try {
            Expense exp = expenseBuilder.build();
            g.addExpense(exp);
            Person paidBy = exp.getPaidBy();
            List<Split> splits = exp.getSplits();

            for (Split split : splits) {
                Person currPerson = split.getPerson();
                if (!currPerson.equals(paidBy)) {
                    currPerson.getBalanceSheet().adjustAmount(paidBy, -1.0 * split.getAmount());
                    paidBy.getBalanceSheet().adjustAmount(currPerson, split.getAmount());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Transaction addTransaction(Person paidBy, Person paidTo, double amount) {
        return new Transaction(paidBy, paidTo, amount);
    }
}
