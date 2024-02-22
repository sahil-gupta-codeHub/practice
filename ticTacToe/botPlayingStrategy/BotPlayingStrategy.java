package ticTacToe.botPlayingStrategy;

import ticTacToe.models.Board;
import ticTacToe.models.Cell;
import ticTacToe.models.Move;

public interface BotPlayingStrategy {
    Cell makeMove(Board board);
}
