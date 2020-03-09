package main.java.com.chessmaster.piece;

import main.java.com.chessmaster.config.Color;
import main.java.com.chessmaster.manager.Field;
import main.java.com.chessmaster.manager.GameBoard;
import main.java.com.chessmaster.manager.GameInfo;

public class Knight extends Piece {

    public Knight(String color, Field field) {
        super(color, field, 5, 3, 8);
        if (color.equals(Color.BLACK)) {
            super.setIconName("bk");
        } else {
            super.setIconName("wk");
        }
    }

    @Override
    public Piece copy(Field field, GameInfo gameInfo, GameBoard gameBoard) {
        Knight pieceCopy = new Knight(this.getColor(), field);
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
        if (Math.abs(currentField.getXPosition() - field.getXPosition()) == 2
                && Math.abs(currentField.getYPosition() - field.getYPosition()) == 1) {
            return true;
        }

        if (Math.abs(currentField.getXPosition() - field.getXPosition()) == 1
                && Math.abs(currentField.getYPosition() - field.getYPosition()) == 2) {
            return true;
        }

        return false;
    }
}
