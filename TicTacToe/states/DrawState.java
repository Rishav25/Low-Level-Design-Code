package TicTacToe.states;

import TicTacToe.GameEngine;
import TicTacToe.exception.InvalidMoveException;
import TicTacToe.models.Player;

public class DrawState implements GameState{
    @Override
    public void makeMove(GameEngine gameEngine, Player p, int x, int y) throws InvalidMoveException{
        throw new InvalidMoveException("Game is drawn, cannot make move");
    }
}
