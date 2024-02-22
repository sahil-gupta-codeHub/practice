package ticTacToe.models;

public class Cell {
    private int row;
    private int col;
    private CellState cellState;
    private char symbol;
    private Player player;

    public Cell(int i, int j) {
        this.row = i;
        this.col = j;
        this.cellState = CellState.EMPTY;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public CellState getCellState() {
        return cellState;
    }

    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void printCell() {
        if(CellState.EMPTY.equals(this.cellState)){
            System.out.print("| - |");
        }
        else if(CellState.FILL.equals(this.cellState)){
            System.out.print("| " + this.symbol + " |");
        }
    }
}
