package ticTacToe.botPlayingStrategy;

import ticTacToe.models.Board;
import ticTacToe.models.Cell;
import ticTacToe.models.CellState;
import ticTacToe.models.Move;

import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayingStrategy{

    @Override
    public Cell makeMove(Board board) {
        for(List<Cell> row:board.getBoard()){
            for(Cell currCell:row){
                if(CellState.EMPTY.equals(currCell.getCellState())){
                    return currCell;
                }
            }
        }
        return null;
    }
}
