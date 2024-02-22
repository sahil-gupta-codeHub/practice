package ticTacToe.models;

import java.util.Scanner;

public class Player {
    private char symbol;
    private PlayerType playerType;
    private String name;
    private int id;
    private Scanner scanner;
    public Player(int id, String name, PlayerType playerType, char symbol){
        this.id = id;
        this.name = name;
        this.playerType = playerType;
        this.symbol = symbol;
        this.scanner = new Scanner(System.in);
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cell makeMove(Board board) {
        System.out.println(this.getName() + " , its your turn. Enter the position");

        int row = scanner.nextInt()-1;
        int col = scanner.nextInt()-1;

        while(!validateMove(row, col, board)){
            System.out.println("Oops!! Wrong position, enter again..");

            row = scanner.nextInt()-1;
            col = scanner.nextInt()-1;
        }

        Cell cell = board.getBoard().get(row).get(col);
        cell.setCellState(CellState.FILL);
        cell.setPlayer(this);
        cell.setSymbol(this.symbol);
        return cell;
    }
    private boolean validateMove(int row, int col, Board board) {
        if(row <0 || row>=board.getDimension())return false;
        if(col<0 || col >= board.getDimension())return false;
        if(board.getBoard().get(row).get(col).getCellState().equals(CellState.FILL))return false;
        return true;
    }

}
