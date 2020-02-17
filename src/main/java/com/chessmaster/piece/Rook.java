package main.java.com.chessmaster.piece;

import main.java.com.chessmaster.config.Color;
import main.java.com.chessmaster.manager.Field;

public class Rook extends Piece {

    public Rook(String color, Field field) {
        super(color, field, 5, 4);
        if (color.equals(Color.BLACK)) {
            this.setIconName("br");
        } else {
            this.setIconName("wr");
        }
    }

    @Override
    public boolean isMoveValid(Field field) {
        if (!super.isMoveValid(field)) {
            return false;
        }

        Field currentField = this.getField();

        //y move
        if (currentField.getXPosition() == field.getXPosition()
                && currentField.getYPosition() != field.getYPosition()) {
            return true;
        }

        //x move
        if (currentField.getXPosition() != field.getXPosition()
                && currentField.getYPosition() == field.getYPosition()) {
            return true;
        }

        return false;
    }
}
