package main.java.com.chessmaster.manager;

import main.java.com.chessmaster.piece.Piece;
import main.java.com.chessmaster.player.Player;

import java.util.ArrayList;
import java.util.List;

public class GameEngineStateInfo {
    private GameBoard gameBoard;
    private GameInfo gameInfo;
    private List<Player> players;
    private Player currentPlayer;

    public GameEngineStateInfo(GameBoard gameBoardToCopy, GameInfo gameInfoToCopy, List<Player> playersToCopy, Player currentPlayerToCopy) {
        gameInfo = gameInfoToCopy.copy();
        players = new ArrayList<>();

        for (Player playerToCopy : playersToCopy) {
            Player player = playerToCopy.copy();
            players.add(player);
            if (currentPlayerToCopy.getColor().equals(player.getColor())) {
                currentPlayer = player;
            }
        }

        gameBoard = new GameBoard(gameBoardToCopy.getSizeX(), gameBoardToCopy.getSizeX());
        for (Field fieldToCopy : gameBoardToCopy.getFields()) {
            if (fieldToCopy.getPiece() != null) {
                Field field = gameBoard.getFieldByPosition(fieldToCopy.getXPosition(), fieldToCopy.getYPosition());
                Piece pieceToCopy = fieldToCopy.getPiece();
                Piece piece = pieceToCopy.copy(field, gameInfo, gameBoard);
                field.setPiece(piece);
            }
        }
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public GameInfo getGameInfo() {
        return gameInfo;
    }

    public void setGameInfo(GameInfo gameInfo) {
        this.gameInfo = gameInfo;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
