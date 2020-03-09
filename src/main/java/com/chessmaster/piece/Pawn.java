package main.java.com.chessmaster.piece;

import main.java.com.chessmaster.config.Color;
import main.java.com.chessmaster.config.PawnMoveDirection;
import main.java.com.chessmaster.manager.Field;
import main.java.com.chessmaster.manager.GameBoard;
import main.java.com.chessmaster.manager.GameInfo;

public class Pawn extends Piece {

    protected PawnMoveDirection moveDirection;
    protected boolean hasMoved;

    public Pawn(String color, Field field, PawnMoveDirection moveDirection) {
        super(color, field, 1, 1, 4);
        this.hasMoved = false;
        this.moveDirection = moveDirection;
        if (color.equals(Color.BLACK)) {
            this.setIconName("bp");
        } else {
            this.setIconName("wp");
        }
    }

    @Override
    public Piece copy(Field field, GameInfo gameInfo, GameBoard gameBoard) {
        Pawn pieceCopy = new Pawn(this.getColor(), field, this.moveDirection);
        pieceCopy.hasMoved = this.hasMoved;
        pieceCopy.setGameBoard(gameBoard);
        pieceCopy.setGameInfo(gameInfo);
        return pieceCopy;
    }

    @Override
    public boolean move(Field field) {
        boolean moveIsValid = super.move(field);
        if (moveIsValid) {
            this.hasMoved = true;
        }
        return moveIsValid;
    }

    @Override
    public boolean isMoveValid(Field field) {
        if (!super.isMoveValid(field)) {
            return false;
        }

        Field currentField = this.getField();

        if (this.moveDirection == PawnMoveDirection.UP) {
            if (currentField.getYPosition() > field.getYPosition()) {
                if (super.gameInfo != null) {
                    super.gameInfo.addMsg("Pawn can not go backwards!");
                } else {
                    System.out.println("Pawn can not go backwards!");
                }
                return false;
            }
        } else if (this.moveDirection == PawnMoveDirection.DOWN) {
            if (currentField.getYPosition() < field.getYPosition()) {
                if (super.gameInfo != null) {
                    super.gameInfo.addMsg("Pawn can not go backwards!");
                } else {
                    System.out.println("Pawn can not go backwards!");
                }
                return false;
            }
        }

        if (!hasMoved && currentField.getXPosition() == field.getXPosition()
                && (Math.abs(currentField.getYPosition() - field.getYPosition()) == 1
                || Math.abs(currentField.getYPosition() - field.getYPosition()) == 2)
                && field.isEmpty()) {
            if (this.gameBoard != null && Math.abs(currentField.getYPosition() - field.getYPosition()) == 2) {
                int yPos = this.moveDirection == PawnMoveDirection.UP ? currentField.getYPosition() + 1 : currentField.getYPosition() - 1;
                Field fieldOnPath = this.gameBoard.getFieldByPosition(currentField.getXPosition(), yPos);
                if (fieldOnPath != null && !fieldOnPath.isEmpty()) {
                    return false;
                }
            }

            return true;
        }

        if (hasMoved && currentField.getXPosition() == field.getXPosition()
                && Math.abs(currentField.getYPosition() - field.getYPosition()) == 1 && field.isEmpty()) {
            return true;
        }

        if (Math.abs(currentField.getXPosition() - field.getXPosition()) == 1
                && Math.abs(currentField.getYPosition() - field.getYPosition()) == 1 && !field.isEmpty()
                && this.isEnemy(field.getPiece())) {
            return true;
        }

        return false;
    }
}
