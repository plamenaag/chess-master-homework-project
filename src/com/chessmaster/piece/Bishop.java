package com.chessmaster.piece;

import com.chessmaster.manager.Field;

public class Bishop extends Piece {

    public Bishop(String color, Field field) {
        super(color, field, 5, 2);
    }

    @Override
    public boolean isMoveValid(Field field) {
        if (!super.isMoveValid(field)) {
            return false;
        }

        Field currentField = this.getField();

        if (Math.abs(currentField.getXPosition() - field.getXPosition())
                == Math.abs(currentField.getYPosition() - field.getYPosition())) {
            return true;
        }

        return false;
    }
}
