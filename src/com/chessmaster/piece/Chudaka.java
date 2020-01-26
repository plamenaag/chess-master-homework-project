package com.chessmaster.piece;

import com.chessmaster.config.PawnMoveDirection;
import com.chessmaster.manager.Field;

@SuppressWarnings("ALL")
public class Chudaka extends Pawn implements Blastable {

    public Chudaka(String color, Field field, PawnMoveDirection moveDirection) {
        super(color, field, moveDirection);
        super.setPower(15);
        super.setId(7);
    }

    @Override
    public boolean blast(Field field) {
        if (isBlastValid(field)) {
            if (field.isEmpty()) {
                System.out.println("Can not blast empty field!");
                return false;
            } else if (this.isEnemy(field.getPiece())) {
                field.getPiece().kill();
                return true;
            } else {
                System.out.println("Field occupied by teammate piece. Can not blast");
                return false;
            }
        } else {
            System.out.println("Blast is not valid!");
            return false;
        }
    }

    public boolean isBlastValid(Field field) {

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
