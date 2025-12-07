import builder.ExpenseBuilder;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

    public void simplifyDebts(Group g) {
        // logic -> consider only the balances within the members of this group and
        // after that is done
        // take the highest debtor and match them against the highest creditor

        Set<Person> personSet = g.getPersonSet();
        Map<Person, Double> personAmountMap = new HashMap<>();

        for (Person person : personSet) {
            for (Map.Entry<Person, Double> en : person.getBalanceSheetMap().entrySet()) {
                Person p = en.getKey();
                Double val = en.getValue();

                if (personSet.contains(p)) {
                    // put the amount in the personAmountMap
                    // next adjusted the sheets since fresh values will be inserted now
                    personAmountMap.put(person, personAmountMap.getOrDefault(person, 0.0) + val);
                    personAmountMap.put(p, personAmountMap.getOrDefault(p, 0.0) + (-1.0 * val));
                    person.getBalanceSheet().adjustAmount(p, val * (-1.0));
                    p.getBalanceSheet().adjustAmount(person, val);
                }
            }
        }

        List<Map.Entry<Person, Double>> creditorsList = new ArrayList<>();
        List<Map.Entry<Person, Double>> debtorsList = new ArrayList<>();

        for (Map.Entry<Person, Double> en : personAmountMap.entrySet()) {
            if (en.getValue() > 0)
                creditorsList.add(en);
            else
                debtorsList.add(en);
        }

        creditorsList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        debtorsList.sort(Map.Entry.comparingByValue());

        int i = 0, j = 0;
        while (i < creditorsList.size() && j < debtorsList.size()) {
            Map.Entry<Person, Double> creditor = creditorsList.get(i);
            Map.Entry<Person, Double> debtor = debtorsList.get(j);

            double settleAmount = Math.min(creditor.getValue(), debtor.getValue() * (-1));

            creditor.getKey().getBalanceSheet().adjustAmount(debtor.getKey(), settleAmount);
            debtor.getKey().getBalanceSheet().adjustAmount(creditor.getKey(), -1 * (settleAmount));

            creditor.setValue(creditor.getValue() - settleAmount);
            debtor.setValue(debtor.getValue() + settleAmount);

            // if creditor is done then we move the creditList
            if (creditor.getValue() < 0.01)
                i++;
            // if debtor is done then we move the debtorList
            if (debtor.getValue() > -0.01)
                j++;

        }

    }
}
