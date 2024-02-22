package ticTacToe.winningStrategies;

import ticTacToe.models.Board;
import ticTacToe.models.Move;

import java.util.HashMap;

public class DiagonalWinningStrategy implements WinningStrategy{
    HashMap<Character, Integer> leftDiagonalMap = new HashMap<>();
    HashMap<Character, Integer> rightDiagonalMap = new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        char symbol = move.getPlayer().getSymbol();

        if(row==col){
            if(!leftDiagonalMap.containsKey(symbol)){
                leftDiagonalMap.put(symbol,0);
            }
            leftDiagonalMap.put(symbol, leftDiagonalMap.get(symbol)+1);
            if(leftDiagonalMap.get(symbol) == board.getDimension())return true;
        }
        if(row+col == board.getDimension()-1){
            if(!rightDiagonalMap.containsKey(symbol)){
                rightDiagonalMap.put(symbol,0);
            }
            rightDiagonalMap.put(symbol, rightDiagonalMap.get(symbol)+1);
            if(rightDiagonalMap.get(symbol) == board.getDimension())return true;
        }
        return false;
    }
    public void performUndo(Move move, Board board){
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        char symbol = move.getPlayer().getSymbol();

        if(row==col){
            leftDiagonalMap.put(symbol, leftDiagonalMap.get(symbol)-1);
        }
        if(row+col==board.getDimension()){
            rightDiagonalMap.put(symbol, rightDiagonalMap.get(symbol)-1);
        }
    }
}
