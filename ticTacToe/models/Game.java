package ticTacToe.models;

import ticTacToe.exception.DuplicateSymbolException;
import ticTacToe.exception.InvalidPlayerCountException;
import ticTacToe.exception.InvlalidBotCountException;
import ticTacToe.winningStrategies.WinningStrategy;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Game {
    private GameState gameState;
    private Board board;
    private Player winner;
    private List<Move> moves;
    private List<WinningStrategy> winningStrategies;
    private List<Player> players;
    private int nextPlayerInd;

    private Game(Builder builder) {
        this.board = new Board(builder.dimension);
        this.players = builder.players;
        this.moves = new ArrayList<>();
        this.winningStrategies = builder.winningStrategies;
        this.nextPlayerInd = 0;
        this.gameState = GameState.IN_PROG;
    }

    public GameState getGameState() {
        return gameState;
    }

    public Board getBoard() {
        return board;
    }

    public Player getWinner() {
        return winner;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getNextPlayerInd() {
        return nextPlayerInd;
    }

    public static Builder createrBuilder(){
        return new Builder();
    }

    public void printBoard() {
        board.printBoard();
    }

    public void makeMove() {
        Player currPlayer = players.get(nextPlayerInd);
        Cell cell = currPlayer.makeMove(board);
        Move lastMove = new Move(cell, currPlayer);
        moves.add(lastMove);
        if(checkWinner(lastMove, board)){
            gameState = GameState.SUCCESS;
            winner = currPlayer;
            return;
        }
        if(moves.size() == board.getDimension()*board.getDimension()){
            gameState = GameState.DRAW;
            return;
        }
        nextPlayerInd++;
        nextPlayerInd = nextPlayerInd % players.size();
    }

    private boolean checkWinner(Move lastMove, Board board) {
        for(WinningStrategy winningStrategy:winningStrategies){
            if(winningStrategy.checkWinner(board, lastMove)){
                return true;
            }
        }
        return false;
    }

    public void perfomUndo() {
        if(moves.size()==0){
            System.out.println("You cannot undo since no move has been taken yet");
            return;
        }
        Move lastMove = moves.get(moves.size()-1);
        moves.remove(moves.size()-1);
        Cell currCell = lastMove.getCell();
        currCell.setCellState(CellState.EMPTY);
        currCell.setPlayer(null);
        currCell.setSymbol('-');

        for(WinningStrategy winningStrategy:winningStrategies){
            winningStrategy.performUndo(lastMove,board);
        }

        if(nextPlayerInd!=0){
            nextPlayerInd--;
        }
        else{
            nextPlayerInd = players.size()-1;
        }
    }


    public static class Builder{
        private int dimension;
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;
        private Builder(){
            this.players = new ArrayList<>();
            this.winningStrategies = new ArrayList<>();
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public Game build() throws DuplicateSymbolException, InvalidPlayerCountException, InvlalidBotCountException {
            //validate the requirements
            validateUniqueSymbol();
            validatePlayersCount();
            validateBotCount();
            return new Game(this);
        }

        private void validateBotCount() throws InvlalidBotCountException {
            int botCount = 0;
            for(Player player:players){
                if(PlayerType.BOT.equals(player.getPlayerType())){
                    botCount++;
                }
            }
            if(botCount > 1){
                throw new InvlalidBotCountException();
            }

        }

        private void validatePlayersCount() throws InvalidPlayerCountException {
            if(players.size() != dimension-1){
                throw new InvalidPlayerCountException();
            }
        }

        private void validateUniqueSymbol() throws DuplicateSymbolException {
            HashSet<Character> symbolSet = new HashSet<>();
            for(Character symbol:symbolSet){
                if(symbolSet.contains(symbol)){
                    throw new DuplicateSymbolException();
                }
            }
        }
    }


}
