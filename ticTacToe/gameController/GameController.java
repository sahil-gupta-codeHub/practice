package ticTacToe.gameController;

import ticTacToe.exception.DuplicateSymbolException;
import ticTacToe.exception.InvalidPlayerCountException;
import ticTacToe.exception.InvlalidBotCountException;
import ticTacToe.models.Game;
import ticTacToe.models.Player;
import ticTacToe.winningStrategies.WinningStrategy;

import java.util.List;

public class GameController {

    public Game startGame(int dimension, List<WinningStrategy> winningStrategies, List<Player> players) throws DuplicateSymbolException, InvlalidBotCountException, InvalidPlayerCountException {
        Game game = Game.createrBuilder()
                .setDimension(dimension)
                .setPlayers(players)
                .setWinningStrategies(winningStrategies)
                .build();
        return game;
    }
    public void printBoard(Game game){
        game.printBoard();
    }
    public void makeMove(Game game){
        game.makeMove();
    }
    public void Undo(Game game){
        game.perfomUndo();
    }
}
