package ticTacToe.winningStrategies;

import ticTacToe.models.Board;
import ticTacToe.models.Move;

import java.util.HashMap;

public class ColumnWInningStrategy implements WinningStrategy{
    HashMap<Integer, HashMap<Character, Integer>> colMap = new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {
        int col = move.getCell().getCol();
        char symbol = move.getPlayer().getSymbol();

        if(!colMap.containsKey(col)){
            colMap.put(col, new HashMap<Character, Integer>());
        }
        if(!colMap.get(col).containsKey(symbol)){
            colMap.get(col).put(symbol,0);
        }
        colMap.get(col).put(symbol, colMap.get(col).get(symbol)+1);
        return colMap.get(col).get(symbol) == board.getDimension();
    }
    public void performUndo(Move move, Board board){
        int col = move.getCell().getCol();
        char symbol = move.getPlayer().getSymbol();

        colMap.get(col).put(symbol, colMap.get(col).get(symbol)-1);
    }
}
