package main.java.com.chessmaster.piece;

import main.java.com.chessmaster.config.Color;
import main.java.com.chessmaster.manager.Field;

@SuppressWarnings("ALL")
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
            if (this.gameBoard != null) {
                int xPos = currentField.getXPosition();
                if (currentField.getYPosition() < field.getYPosition()) {
                    int yPos = currentField.getYPosition() + 1;
                    while (yPos < field.getYPosition()) {
                        Field fieldOnPath = this.gameBoard.getFieldByPosition(xPos, yPos);
                        if (fieldOnPath != null && !fieldOnPath.isEmpty()) {
                            return false;
                        }
                        yPos++;
                    }
                } else if (currentField.getYPosition() > field.getYPosition()) {
                    int yPos = currentField.getYPosition() - 1;
                    while (yPos > field.getYPosition()) {
                        Field fieldOnPath = this.gameBoard.getFieldByPosition(xPos, yPos);
                        if (fieldOnPath != null && !fieldOnPath.isEmpty()) {
                            return false;
                        }
                        yPos--;
                    }
                }
            }

            return true;
        }

        // x move
        if (currentField.getXPosition() != field.getXPosition()
                && currentField.getYPosition() == field.getYPosition()) {
            if (this.gameBoard != null) {
                int yPos = currentField.getYPosition();
                if (currentField.getXPosition() < field.getXPosition()) {
                    int xPos = currentField.getXPosition() + 1;
                    while (xPos < field.getXPosition()) {
                        Field fieldOnPath = this.gameBoard.getFieldByPosition(xPos, yPos);
                        if (fieldOnPath != null && !fieldOnPath.isEmpty()) {
                            return false;
                        }
                        xPos++;
                    }
                } else if (currentField.getXPosition() > field.getXPosition()) {
                    int xPos = currentField.getXPosition() - 1;
                    while (xPos > field.getXPosition()) {
                        Field fieldOnPath = this.gameBoard.getFieldByPosition(xPos, yPos);
                        if (fieldOnPath != null && !fieldOnPath.isEmpty()) {
                            return false;
                        }
                        xPos--;
                    }
                }
            }

            return true;
        }

        return false;
    }
}
