package ticTacToe.winningStrategies;

import ticTacToe.models.Board;
import ticTacToe.models.Move;

public interface WinningStrategy {
    boolean checkWinner(Board board, Move move);
    void performUndo(Move move, Board board);
}
