package TicTacToe.models;

import TicTacToe.enums.Symbol;

public class Board {
    int size;
    Cell [][] board;

    public Board(int size){
        this.size = size;
        board = new Cell[size][size];
        initializeBoard();
    }

    private void initializeBoard(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                board[i][j] = new Cell(Symbol.EMPTY);
            }
        }
    }

    public Cell getCell(int x, int y){
        return board[x][y];
    }

    public void setCellSymbol(int x, int y, Symbol symbol){
        board[x][y].setSymbol(symbol);
    }

    public Symbol getCellSymbol(int x, int y){
        return board[x][y].getSymbol();
    }

    public int getSize(){
        return this.size;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("-----------------------------------\n");
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                sb.append(getCell(i, j).getSymbol().getSign());
            }
            sb.append("\n");
        }
        sb.append("-----------------------------------\n");
        return sb.toString();
    }
}
