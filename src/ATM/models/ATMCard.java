package models;

public class ATMCard {
    String cardNumber;
    String expiryDate;
    String nameOnCard;
    String cvv;
    String pin;
    BankAccount bankAccount;

    public ATMCard(String cardNumber, String expiryDate, String nameOnCard, String cvv) {
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.nameOnCard = nameOnCard;
        this.cvv = cvv;
    }

    public void linkBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public BankAccount getBankAccount() {
        return this.bankAccount;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getPin() {
        return this.pin;
    }

}
