package ticTacToe.winningStrategies;

import ticTacToe.models.Board;
import ticTacToe.models.Move;

import java.util.HashMap;
import java.util.HashSet;

public class RowWinningStrategy implements WinningStrategy{
    HashMap<Integer,HashMap<Character, Integer>> rowMap = new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {

        int row = move.getCell().getRow();
        char symbol = move.getPlayer().getSymbol();

        if(!rowMap.containsKey(row)){
            rowMap.put(row, new HashMap<Character, Integer>());
        }
        if(!rowMap.get(row).containsKey(symbol)){
            rowMap.get(row).put(symbol,0);
        }
        rowMap.get(row).put(symbol, rowMap.get(row).get(symbol)+1);
        return rowMap.get(row).get(symbol) == board.getDimension();
    }
    public void performUndo(Move move,Board board){
        int row = move.getCell().getRow();
        char symbol = move.getPlayer().getSymbol();

        rowMap.get(row).put(symbol, rowMap.get(row).get(symbol)-1);
    }
}
