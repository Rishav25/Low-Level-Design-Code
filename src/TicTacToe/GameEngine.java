package TicTacToe;

import TicTacToe.enums.Symbol;
import TicTacToe.enums.GameStateEnum;
import TicTacToe.exception.InvalidMoveException;
import TicTacToe.models.Board;
import TicTacToe.models.Player;
import TicTacToe.states.DrawState;
import TicTacToe.states.GameState;
import TicTacToe.states.InProgresState;
import TicTacToe.states.WinnerState;

public class GameEngine {

    GameState gameState;
    GameStateEnum gameStateEnum;
    Player p1;
    Player p2;
    Player currentPlayer;
    Player winnerPlayer;
    Board board;

    public GameEngine(Player p1, Player p2, int size) {
        this.gameState = new InProgresState();
        this.gameStateEnum = GameStateEnum.IN_PROGRESS;
        this.board = new Board(size);
        this.p1 = p1;
        this.p2 = p2;
        currentPlayer = p1;
    }

    public void makeMove(Player p, int x, int y) throws InvalidMoveException {
        gameState.makeMove(this, p, x, y);
    }

    public void changePlayer() {
        currentPlayer = (currentPlayer.equals(p1)) ? p2 : p1;
    }

    public void checkBoardStatus() {
        boolean hasWinner = getWinnerStatus(board, currentPlayer.getSymbol());
        if (hasWinner) {
            winnerPlayer = currentPlayer;
            gameState = new WinnerState();
            gameStateEnum = winnerPlayer.getSymbol().equals(Symbol.X) ? GameStateEnum.WINNER_X : GameStateEnum.WINNER_O;
            System.out.println("We have a winner " + winnerPlayer.getName() + "  " + winnerPlayer.getSymbol());
            return;
        }
        boolean hasDrawn = getDrawnStatus(board);
        if (hasDrawn) {
            gameState = new DrawState();
            gameStateEnum = GameStateEnum.DRAW;
            System.out.println("Game Drawn");
            return;
        }
    }

    private boolean getWinnerStatus(Board board, Symbol symbol) {
        int size = board.getSize();
        // row check
        for (int i = 0; i < size; i++) {
            int matchCount = 0;
            for (int j = 0; j < size; j++) {
                if (board.getCell(i, j).getSymbol().equals(symbol)) {
                    matchCount++;
                }
            }
            if (matchCount == size) {
                return true;
            }
        }

        // col check
        for (int j = 0; j < size; j++) {
            int matchCount = 0;
            for (int i = 0; i < size; i++) {
                if (board.getCell(j, i).getSymbol().equals(symbol)) {
                    matchCount++;
                }
            }
            if (matchCount == size) {
                return true;
            }
        }

        //diagonal check
        int i = 0, j = 0;
        boolean mainDiagonal = true;
        while (i < size && j < size) {
            if (board.getCell(i, j).getSymbol().equals(symbol)) {
                i++;
                j++;
            } else {
                mainDiagonal = false;
                break;
            }
        }
        i = 0;
        j = size - 1;
        boolean otherDiagonal = true;
        while (i < size && j >= 0) {
            if (board.getCell(i, j).getSymbol().equals(symbol)) {
                i++;
                j--;
            } else {
                otherDiagonal = false;
                break;
            }
        }
        return mainDiagonal || otherDiagonal;
    }

    private boolean getDrawnStatus(Board board) {
        int size = board.getSize();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board.getCell(i, j).getSymbol().equals(Symbol.EMPTY)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void printBoard() {
        System.out.println(board);
    }

    public Player getWinner() {
        return winnerPlayer;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Board getBoard() {
        return board;
    }

    public GameStateEnum getGameStateEnum() {
        return gameStateEnum;
    }

};
