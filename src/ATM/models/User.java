package models;

public class User {
    String userName;
    String phoneNumber;
    BankAccount bankAccount;

    public User(String userName, String phoneNumber) {
        this.userName = userName;
        this.phoneNumber = phoneNumber;
    }

    public void addBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

}
