package main.java.com.chessmaster.piece;

import main.java.com.chessmaster.config.Color;
import main.java.com.chessmaster.manager.Field;
import main.java.com.chessmaster.manager.GameBoard;
import main.java.com.chessmaster.manager.GameInfo;

@SuppressWarnings("ALL")
public class King extends Piece {

    public King(String color, Field field) {
        super(color, field, 6, 5, 12);
        if (color.equals(Color.BLACK)) {
            this.setIconName("bking");
        } else {
            this.setIconName("wking");
        }
    }

    @Override
    public Piece copy(Field field, GameInfo gameInfo, GameBoard gameBoard) {
        King pieceCopy = new King(this.getColor(), field);
        pieceCopy.setGameBoard(gameBoard);
        pieceCopy.setGameInfo(gameInfo);
        return pieceCopy;
    }

    @Override
    public boolean isMoveValid(Field field) {
        if (!super.isMoveValid(field)) {
            return false;
        }

        Field currentField = this.getField();
        // diagonal move
        if (Math.abs(currentField.getXPosition() - field.getXPosition()) == 1
                && Math.abs(currentField.getYPosition() - field.getYPosition()) == 1) {
            return true;
        }

        // y move
        if (Math.abs(currentField.getXPosition() - field.getXPosition()) == 0
                && Math.abs(currentField.getYPosition() - field.getYPosition()) == 1) {
            return true;
        }

        // x move
        if (Math.abs(currentField.getXPosition() - field.getXPosition()) == 1
                && Math.abs(currentField.getYPosition() - field.getYPosition()) == 0) {
            return true;
        }

        return false;
    }
}
