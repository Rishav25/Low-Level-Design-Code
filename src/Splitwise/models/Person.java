package models;

import java.util.UUID;

public class Person {
    String id;
    String name;
    BalanceSheet balanceSheet;

    public Person(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.balanceSheet = new BalanceSheet();
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public BalanceSheet getBalanceSheet() {
        return this.balanceSheet;
    }

}
