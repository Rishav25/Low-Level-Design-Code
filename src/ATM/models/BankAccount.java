package models;

public class BankAccount {
    String accountId;
    float balance;

    public BankAccount(String accountId) {
        this.accountId = accountId;
        this.balance = 0F;
    }

    public float getBalance() {
        return this.balance;
    }

    public void addBalance(float amount) {
        this.balance += amount;
    }

    public void withdrawBalance(float amount) {
        this.balance -= amount;
    }
}
