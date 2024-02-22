package ticTacToe.models;

import ticTacToe.botPlayingStrategy.BotPlayingStrategy;

import java.util.TreeMap;

public class Bot extends Player{
    private BotPlayingStrategy botPlayingStrategy;
    public Bot(int id, String name, PlayerType playerType, char symbol, BotPlayingStrategy botPlayingStrategy) {
        super(id, name, playerType, symbol);
        this.botPlayingStrategy = botPlayingStrategy;
    }
    public Cell makeMove(Board board) {
        System.out.println("Wait for the "+this.getName() + "'s turn");
        Cell cell = botPlayingStrategy.makeMove(board);
        cell.setCellState(CellState.FILL);
        cell.setPlayer(this);
        cell.setSymbol(this.getSymbol());
        return cell;
    }
}
