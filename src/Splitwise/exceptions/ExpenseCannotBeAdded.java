package exceptions;

public class ExpenseCannotBeAdded extends RuntimeException {

    public ExpenseCannotBeAdded(String message) {
        super(message);
    }

}
