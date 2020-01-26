package com.chessmaster.piece;

import com.chessmaster.config.Color;
import com.chessmaster.config.PawnMoveDirection;
import com.chessmaster.manager.Field;

public class PieceFactory {
    public static Piece createPiece(String color, int id, Field field) {
        switch (id) {
            case 1:
                return new Pawn(color, field, color == Color.WHITE ? PawnMoveDirection.UP : PawnMoveDirection.DOWN);
            case 2:
                return new Bishop(color, field);
            case 3:
                return new Knight(color, field);
            case 4:
                return new Rook(color, field);
            case 5:
                return new King(color, field);
            case 6:
                return new Queen(color, field);
            case 7:
                return new Chudaka(color, field, color == Color.WHITE ? PawnMoveDirection.UP : PawnMoveDirection.DOWN);
            default:
                return null;
        }
    }
}
