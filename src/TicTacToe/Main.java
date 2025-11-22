package TicTacToe;

import TicTacToe.enums.Symbol;
import TicTacToe.models.Player;

public class Main {
    public static void main(String[] args) {
        Player p1 = new Player("Rishav", Symbol.X);
        Player p2 = new Player("Meghna", Symbol.O);
        GameEngine gameEngine = new GameEngine(p1, p2, 3);
        
        System.out.println(gameEngine.getBoard());
        gameEngine.makeMove(p1, 2, 2);
        System.out.println(gameEngine.getBoard());
        gameEngine.makeMove(p2 ,1, 1);
        System.out.println(gameEngine.getBoard());
        gameEngine.makeMove(p1, 0, 1);
        System.out.println(gameEngine.getBoard());
        gameEngine.makeMove(p2, 1, 0);
        System.out.println(gameEngine.getBoard());
        gameEngine.makeMove(p1, 2, 1);   
        System.out.println(gameEngine.getBoard());
        gameEngine.makeMove(p2, 1, 2);
        System.out.println(gameEngine.getBoard());
    }
}
