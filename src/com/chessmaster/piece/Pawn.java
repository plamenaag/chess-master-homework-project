package com.chessmaster.piece;

import com.chessmaster.config.PawnMoveDirection;
import com.chessmaster.manager.Field;

public class Pawn extends Piece {

    private PawnMoveDirection moveDirection;
    private boolean hasMoved;

    public Pawn(String color, Field field, PawnMoveDirection moveDirection) {
        super(color, field, 1, 1);
        this.hasMoved = false;
        this.moveDirection = moveDirection;
    }

    @Override
    public boolean isMoveValid(Field field) {
        if (!super.isMoveValid(field)) {
            return false;
        }

        Field currentField = this.getField();

        // check movement direction
        if (this.moveDirection == PawnMoveDirection.UP) {
            if (currentField.getYPosition() > field.getYPosition()) {
                System.out.println("Pawn can not go backwards!");
                return false;
            }
        } else if (this.moveDirection == PawnMoveDirection.DOWN) {
            if (currentField.getYPosition() < field.getYPosition()) {
                System.out.println("Pawn can not go backwards!");
                return false;
            }
        }

        // pawn has not moved from start position and can move two positions
        if (!hasMoved && currentField.getXPosition() == field.getXPosition()
                && (Math.abs(currentField.getYPosition() - field.getYPosition()) == 1
                || Math.abs(currentField.getYPosition() - field.getYPosition()) == 2)
                && field.isEmpty()) {
            return true;
        }

        // pawn has moved from start position and can move only one forward
        if (hasMoved && currentField.getXPosition() == field.getXPosition()
                && Math.abs(currentField.getYPosition() - field.getYPosition()) == 1 && field.isEmpty()) {
            return true;
        }

        // pawn can move by diagonal if there is an enemy piece
        if (Math.abs(currentField.getXPosition() - field.getXPosition()) == 1
                && Math.abs(currentField.getYPosition() - field.getYPosition()) == 1 && !field.isEmpty()
                && this.isEnemy(field.getPiece())) {
            return true;
        }

        return false;
    }
}
