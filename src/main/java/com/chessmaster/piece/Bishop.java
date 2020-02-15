package main.java.com.chessmaster.piece;

import main.java.com.chessmaster.config.Color;
import main.java.com.chessmaster.manager.Field;

public class Bishop extends Piece {

    public Bishop(String color, Field field) {
        super(color, field, 5, 2);
        if(color.equals(Color.BLACK)){
            this.setIconName("bb");
        }else{
            this.setIconName("wb");
        }

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
