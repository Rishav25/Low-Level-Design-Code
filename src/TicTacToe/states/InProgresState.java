package TicTacToe.states;

import TicTacToe.GameEngine;
import TicTacToe.enums.GameStateEnum;
import TicTacToe.enums.Symbol;
import TicTacToe.exception.InvalidMoveException;
import TicTacToe.models.Player;

public class InProgresState implements GameState {
    @Override
    public void makeMove(GameEngine gameEngine, Player p, int x, int y) throws InvalidMoveException{
        if(!p.equals(gameEngine.getCurrentPlayer()))
            throw new InvalidMoveException("Not your move");
        Symbol pSymbol = p.getSymbol();
        if(!gameEngine.getBoard().getCellSymbol(x, y).equals(Symbol.EMPTY))
            throw new InvalidMoveException("Cell already occupied");
        gameEngine.getBoard().setCellSymbol(x, y, pSymbol);
        gameEngine.checkBoardStatus();
        if(gameEngine.getGameStateEnum().equals(GameStateEnum.IN_PROGRESS))
            gameEngine.changePlayer();
    }
}
