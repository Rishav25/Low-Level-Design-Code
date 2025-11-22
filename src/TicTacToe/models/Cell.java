package TicTacToe.models;

import TicTacToe.enums.Symbol;

public class Cell {
    Symbol symbol;
    
    public Cell(Symbol symbol){
        this.symbol = symbol;
    }

    public Symbol getSymbol(){
        return symbol;
    }

    public void setSymbol(Symbol symbol){
        this.symbol = symbol;
    }
}
