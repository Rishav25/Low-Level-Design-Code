package TicTacToe.enums;

public enum GameStateEnum {
    IN_PROGRESS("Game in progres"),
    WINNER_X("Winner is X"),
    WINNER_O("Winner is O"),
    DRAW("Game Drawn");

    private final String message;

    private GameStateEnum(String message) {
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
    
}
