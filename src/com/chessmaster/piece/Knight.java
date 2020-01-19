package com.chessmaster.piece;

import com.chessmaster.manager.Field;

public class Knight extends Piece {

    public Knight(String color, Field field) {
        super(color, field, 5, 3);
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
