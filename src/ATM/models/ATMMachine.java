package models;

import ChainOfResponsibility.CashDispenser;
import State.ATMState;
import State.ATMStatePinNotValidated;
import State.ATMStatePinValidated;
import enums.MenuOptions;
import exceptions.InvalidOperationException;

public class ATMMachine {
    ATMState atmState;
    ATMMenu atmMenu;
    CashDispenser cashDispenser;
    MenuOptions selectedMenuOption;

    public ATMMachine() {
        this.atmState = new ATMStatePinNotValidated();
        this.atmMenu = new ATMMenu();
        this.cashDispenser = new CashDispenser();
        this.selectedMenuOption = MenuOptions.VALIDATE_PIN;
    }

    public String displayMenuOptions() {
        StringBuilder menuOptionsSB = new StringBuilder();
        for (MenuOptions menuOption : this.atmMenu.getMenuOptions()) {
            menuOptionsSB.append(menuOption.getDisplayString() + "\n");
        }
        return menuOptionsSB.toString();
    }

    public void setMenuOption(MenuOptions menuOption) {
        this.selectedMenuOption = menuOption;
    }

    public void validatePin(ATMCard atmCard, String pin) {
        try {
            checkOperation(MenuOptions.VALIDATE_PIN);
            if (atmState.validatePin(atmCard, pin) == true)
                atmState = new ATMStatePinValidated();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getBalance(ATMCard atmCard) {
        try {
            checkOperation(MenuOptions.DISPLAY_BALANCE);
            float balance = atmState.displayBalance(atmCard);
            System.out.println("Current Balance : " + balance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void depositBalance(ATMCard atmCard, int amount) {
        try {
            checkOperation(MenuOptions.DEPOSIT_CASH);
            atmState.depositBalance(atmCard, amount);
        } catch (

        Exception e) {
            e.printStackTrace();
        }
    }

    public void withdrawBalance(ATMCard atmCard, int amount) {
        try {
            checkOperation(MenuOptions.WITHDRAW_CASH);
            if (cashDispenser.canDisburseAmount(amount)) {
                cashDispenser.disburseAmount(amount);
                atmState.withdrawBalance(atmCard, amount);
            }
            System.out.println("Current Balance : " + atmState.displayBalance(atmCard));
            atmState = new ATMStatePinNotValidated();
            selectedMenuOption = MenuOptions.VALIDATE_PIN;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkOperation(MenuOptions reqOption) throws InvalidOperationException {
        if (!selectedMenuOption.equals(reqOption))
            throw new InvalidOperationException("Please select the correct option");
    }

    public CashDispenser getCashDispenser() {
        return this.cashDispenser;
    }

}
