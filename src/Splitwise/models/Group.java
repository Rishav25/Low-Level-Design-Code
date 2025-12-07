package models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Group {
    Set<Person> personSet;
    String groupName;
    String groupId;
    List<Expense> expenseList;

    public Group(String groupName) {
        this.groupName = groupName;
        this.groupId = UUID.randomUUID().toString();
        this.personSet = new HashSet<>();
        this.expenseList = new ArrayList<>();
    }

    public void addPerson(Person p) {
        this.personSet.add(p);
    }

    public void removePerson(Person p) {
        if (personSet.contains(p))
            personSet.remove(p);
    }

    public void addExpense(Expense e) {
        expenseList.add(e);
    }

    public void removeExpense(Expense e) {
        if (expenseList.contains(e))
            expenseList.remove(e);
    }

    public Set<Person> getPersonSet() {
        return this.personSet;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public List<Expense> getExpenseList() {
        return this.expenseList;
    }

}
