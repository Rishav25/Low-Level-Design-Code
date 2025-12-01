package enums;

public enum MenuOptions {
    VALIDATE_PIN("Validate Pin"),
    DISPLAY_BALANCE("Display Balance"),
    WITHDRAW_CASH("Withdraw Cash"),
    DEPOSIT_CASH("Deposit Cash");

    private String displayString;

    private MenuOptions(String displayString) {
        this.displayString = displayString;
    }

    public String getDisplayString() {
        return this.displayString;
    }
}
