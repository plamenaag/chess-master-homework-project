package main.java.com.chessmaster.piece;

import main.java.com.chessmaster.config.Color;
import main.java.com.chessmaster.manager.Field;

@SuppressWarnings("ALL")
public class Queen extends Piece {

    public Queen(String color, Field field) {
        super(color, field, 10, 6);
        if (color.equals(Color.BLACK)) {
            this.setIconName("bq");
        } else {
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

        // diagonal move
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
