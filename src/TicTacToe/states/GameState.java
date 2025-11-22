package TicTacToe.states;

import TicTacToe.GameEngine;
import TicTacToe.exception.InvalidMoveException;
import TicTacToe.models.Player;

public interface GameState {
    public void makeMove(GameEngine gameEngine, Player p, int x, int y) throws InvalidMoveException;
}
