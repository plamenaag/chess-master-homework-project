package com.chessmaster.piece;

import com.chessmaster.manager.Field;

public class King extends Piece {

    public King(String color, Field field) {
        super(color, field, 6, 5);
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
