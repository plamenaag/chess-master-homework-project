package main.java.com.chessmaster.piece;

import main.java.com.chessmaster.config.Color;
import main.java.com.chessmaster.manager.Field;

@SuppressWarnings("ALL")
public class Bishop extends Piece {

    public Bishop(String color, Field field) {
        super(color, field, 5, 2, 6);
        if (color.equals(Color.BLACK)) {
            this.setIconName("bb");
        } else {
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

            if (this.gameBoard != null) {
                int xMov = currentField.getXPosition() > field.getXPosition() ? -1 : 1;
                int yMov = currentField.getYPosition() > field.getYPosition() ? -1 : 1;
                int xPos = currentField.getXPosition() + xMov;
                int yPos = currentField.getYPosition() + yMov;
                Field fieldOnPath = this.gameBoard.getFieldByPosition(xPos, yPos);
                while (fieldOnPath != field) {
                    if (fieldOnPath != null && !fieldOnPath.isEmpty()) {
                        return false;
                    }

                    yPos += yMov;
                    xPos += xMov;
                    fieldOnPath = this.gameBoard.getFieldByPosition(xPos, yPos);
                }
            }

            return true;
        }

        return false;
    }
}
