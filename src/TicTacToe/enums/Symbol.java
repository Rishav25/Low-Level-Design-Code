package TicTacToe.enums;

public enum Symbol {
    X('X'),
    O('O'),
    EMPTY('.');

    private final char sign;

    private Symbol(char sign) {
        this.sign = sign;
    }

    public char getSign() {
        return this.sign;
    }

}
