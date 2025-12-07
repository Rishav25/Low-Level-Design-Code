package models;

public class Split {
    Person person;
    double amount;

    public Split(Person p, double amount) {
        this.person = p;
        this.amount = amount;
    }

    public Person getPerson() {
        return this.person;
    }

    public double getAmount() {
        return this.amount;
    }

}
