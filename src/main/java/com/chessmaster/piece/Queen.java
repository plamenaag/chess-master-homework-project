package main.java.com.chessmaster.piece;

import main.java.com.chessmaster.config.Color;
import main.java.com.chessmaster.manager.Field;

public class Queen extends Piece {

    public Queen(String color, Field field) {
        super(color, field, 10, 6);
        if(color.equals(Color.BLACK)){
            this.setIconName("bq");
        }else{
            this.setIconName("wq");
        }
    }

    @Override
    public boolean isMoveValid(Field field) {
        if (!super.isMoveValid(field)) {
            return false;
        }

        Field currentField = this.getField();
        // y move
        if (currentField.getXPosition() == field.getXPosition()
                && currentField.getYPosition() != field.getYPosition()) {
            return true;
        }

        // x move
        if (currentField.getXPosition() != field.getXPosition()
                && currentField.getYPosition() == field.getYPosition()) {
            return true;
        }

        // diagonal move
        if (Math.abs(currentField.getXPosition() - field.getXPosition())
                == Math.abs(currentField.getYPosition() - field.getYPosition())) {
            return true;
        }

        return false;
    }
}
