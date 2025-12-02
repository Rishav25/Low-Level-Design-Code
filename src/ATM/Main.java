/*
Models -> 
Bank HAS A User HAS A BankAccount HAS A ATMCard
ATMMachine HAS A Menu HAS MenuOptions
MenuOptions -> ENUM (VALIDATE_PIN, CHANGE_PIN, SHOW_BALANCE, WITHDRAW_AMOUNT)
CashDispenser -> IS A 500CashDispenser, 200CashDispenser, 100CashDispenser, 50CashDispenser
Chain of Responsibility which is going to help us determine if we can dispense the cash
and if we can, what would be the denominations. Will make the code extensible. 

Bank, User, BankAccount, ATMCard, ATMMachine, Menu, MenuOptions, CashDispenser
Design Patterns -> ChainOfResponsibility, Observer Design Pattern to update the balance if any amount is deposited or withdrawn
 */

import enums.MenuOptions;
import models.ATMCard;
import models.ATMMachine;
import models.Bank;
import models.BankAccount;
import models.User;

public class Main {
    public static void main(String[] args) {
        // Bank, BankAccount and ATMCard Setup
        Bank b1 = new Bank("Falana Dhikana Bank", "FDB000923");
        User u1 = new User("Rishav Panda", "8997875674");
        User u2 = new User("Meghna Jha", "8877744432");
        BankAccount ba1 = new BankAccount("92928383839");
        BankAccount ba2 = new BankAccount("939393939020");
        u1.addBankAccount(ba1);
        u2.addBankAccount(ba2);

        ba1.addBalance(150000.00F);
        ba2.addBalance(325000.50F);

        ATMCard atmC1 = new ATMCard("2223-3333-4444-5555", "11/30", "Rishav Panda", "986");
        ATMCard atmC2 = new ATMCard("2223-3333-4444-6554", "11/32", "Meghna Jha", "756");
        atmC1.setPin("1234");
        atmC2.setPin("9876");
        atmC1.linkBankAccount(ba1);
        atmC2.linkBankAccount(ba2);

        // ATM
        ATMMachine atm = new ATMMachine();

        System.out.println(atm.getCashDispenser().getNoteCounts());

        atm.validatePin(atmC1, "1234");
        atm.displayMenuOptions();
        atm.setMenuOption(MenuOptions.DISPLAY_BALANCE);
        atm.getBalance(atmC1);
        atm.setMenuOption(MenuOptions.WITHDRAW_CASH);
        atm.withdrawBalance(atmC1, 2500);
        System.out.println(atm.getCashDispenser().getNoteCounts());

        atm.validatePin(atmC2, "9876");
        atm.displayMenuOptions();
        atm.setMenuOption(MenuOptions.DISPLAY_BALANCE);
        atm.getBalance(atmC2);
        atm.setMenuOption(MenuOptions.WITHDRAW_CASH);
        atm.withdrawBalance(atmC2, 5000);
        System.out.println(atm.getCashDispenser().getNoteCounts());

        atm.getBalance(atmC2);

        atm.validatePin(atmC2, "9876");
        atm.setMenuOption(MenuOptions.DISPLAY_BALANCE);
        atm.getBalance(atmC2);
        atm.setMenuOption(MenuOptions.WITHDRAW_CASH);
        atm.withdrawBalance(atmC2, 300);
        System.out.println(atm.getCashDispenser().getNoteCounts());

        atm.validatePin(atmC2, "9876");
        atm.setMenuOption(MenuOptions.DISPLAY_BALANCE);
        atm.getBalance(atmC2);
        atm.setMenuOption(MenuOptions.WITHDRAW_CASH);
        atm.withdrawBalance(atmC2, 10000);
        System.out.println(atm.getCashDispenser().getNoteCounts());

    }
}
