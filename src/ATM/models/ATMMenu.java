package models;

import java.util.LinkedList;
import java.util.List;

import enums.MenuOptions;

public class ATMMenu {
    List<MenuOptions> menuOptions;

    public ATMMenu() {
        menuOptions = new LinkedList<>();
        menuOptions.add(MenuOptions.VALIDATE_PIN);
        menuOptions.add(MenuOptions.DISPLAY_BALANCE);
        menuOptions.add(MenuOptions.WITHDRAW_CASH);
        menuOptions.add(MenuOptions.DEPOSIT_CASH);
    }

    public List<MenuOptions> getMenuOptions() {
        return this.menuOptions;
    }

}
