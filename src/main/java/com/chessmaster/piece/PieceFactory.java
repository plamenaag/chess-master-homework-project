package main.java.com.chessmaster.piece;

import main.java.com.chessmaster.config.PawnMoveDirection;
import main.java.com.chessmaster.manager.Field;
import main.java.com.chessmaster.config.Color;
import main.java.com.chessmaster.manager.GameInfo;

public class PieceFactory {
    public static Piece createPiece(String color, int id, Field field, GameInfo gameInfo) {
        Piece piece = null;
        switch (id) {
            case 1:
                piece = new Pawn(color, field, color == Color.WHITE ? PawnMoveDirection.UP : PawnMoveDirection.DOWN);
                break;
            case 2:
                piece = new Bishop(color, field);
                break;
            case 3:
                piece = new Knight(color, field);
                break;
            case 4:
                piece = new Rook(color, field);
                break;
            case 5:
                piece = new King(color, field);
                break;
            case 6:
                piece = new Queen(color, field);
                break;
            case 7:
                piece = new Chudaka(color, field, color == Color.WHITE ? PawnMoveDirection.UP : PawnMoveDirection.DOWN);
                break;
            default:
                piece = null;
        }
        piece.setGameInfo(gameInfo);
        return piece;
    }
}
