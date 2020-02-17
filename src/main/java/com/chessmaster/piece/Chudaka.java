package main.java.com.chessmaster.piece;

import main.java.com.chessmaster.config.Color;
import main.java.com.chessmaster.config.PawnMoveDirection;
import main.java.com.chessmaster.manager.Field;

@SuppressWarnings("ALL")
public class Chudaka extends Pawn implements Blastable {

    public Chudaka(String color, Field field, PawnMoveDirection moveDirection) {
        super(color, field, moveDirection);
        super.setPower(15);
        super.setId(7);
        if (color.equals(Color.BLACK)) {
            this.setIconName("bch");
        } else {
            this.setIconName("wch");
        }
    }

    @Override
    public boolean blast(Field field) {
        if (isBlastValid(field)) {
            if (field.isEmpty()) {
                if (super.gameInfo != null) {
                    super.gameInfo.addMsg("Can not blast empty field!");
                } else {
                    System.out.println("Can not blast empty field!");
                }
                return false;
            } else if (this.isEnemy(field.getPiece())) {
                field.getPiece().kill();
                return true;
            } else {
                if (super.gameInfo != null) {
                    super.gameInfo.addMsg("Field occupied by teammate piece. Can not blast");
                } else {
                    System.out.println("Field occupied by teammate piece. Can not blast");
                }

                return false;
            }
        } else {
            if (super.gameInfo != null) {
                super.gameInfo.addMsg("Blast is not valid!");
            } else {
                System.out.println("Blast is not valid!");
            }

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
