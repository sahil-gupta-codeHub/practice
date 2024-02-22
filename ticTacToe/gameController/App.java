package ticTacToe.gameController;

import ticTacToe.botPlayingStrategy.BotPlayingStrategyFactory;
import ticTacToe.exception.DuplicateSymbolException;
import ticTacToe.exception.InvalidPlayerCountException;
import ticTacToe.exception.InvlalidBotCountException;
import ticTacToe.models.*;
import ticTacToe.winningStrategies.ColumnWInningStrategy;
import ticTacToe.winningStrategies.DiagonalWinningStrategy;
import ticTacToe.winningStrategies.RowWinningStrategy;
import ticTacToe.winningStrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws DuplicateSymbolException, InvlalidBotCountException, InvalidPlayerCountException {
        System.out.println("------------ Welcome to the game------------");
        Scanner scanner = new Scanner(System.in);
        GameController gameController = new GameController();
        List<Player> players = new ArrayList<>();
        List<WinningStrategy> winningStrategyList = new ArrayList<>();


        players.add(new Player(1, "Sahil", PlayerType.HUMAN,'X'));
        //players.add(new Bot(2, "Robo", PlayerType.BOT,'O', BotPlayingStrategyFactory.getBotPlayingStrategy(BotDifficultyLevel.EASY)));
        players.add(new Bot(2, "Robo", PlayerType.BOT,'O', BotPlayingStrategyFactory.getBotPlayingStrategy(BotDifficultyLevel.EASY)));

        winningStrategyList.add(new RowWinningStrategy());
        winningStrategyList.add(new ColumnWInningStrategy());
        winningStrategyList.add(new DiagonalWinningStrategy());

        Game game = gameController.startGame(3, winningStrategyList, players);

        while(game.getGameState().equals(GameState.IN_PROG)){
            gameController.printBoard(game);
            System.out.println("Do you want to undo last move - Y/N");
            String option = scanner.next();
            if(option.equalsIgnoreCase("Y")){
                gameController.Undo(game);
                continue;
            }
            if(option.equalsIgnoreCase("N")){
                gameController.makeMove(game);
            }
            else{
                System.out.println("Please choose a valid option.");
            }
        }
        game.printBoard();
        if(GameState.DRAW.equals(game.getGameState())){
            System.out.println("----------------------------Its a draw..----------------------------");
        }
        else if(GameState.SUCCESS.equals(game.getGameState())){
            System.out.println("-------------------------------" + game.getWinner().getName() + " is the Winner...-------------------------------");
        }
    }
}
