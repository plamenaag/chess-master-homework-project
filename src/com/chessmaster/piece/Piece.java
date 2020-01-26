package com.chessmaster.piece;

import com.chessmaster.config.Color;
import com.chessmaster.manager.Field;
import com.chessmaster.manager.Rendable;

public abstract class Piece implements Rendable {
    private String color;
    private int power;
    private int id;
    private Field field;

    protected Piece(String color, Field field, int power, int id) {
        this.color = color;
        this.field = field;
        this.power = power;
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public int getPower() {
        return power;
    }

    public int getId() {
        return id;
    }

    protected void setPower(int power) {
        this.power = power;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public boolean move(Field field) {
        if (isMoveValid(field)) {
            if (field.isEmpty()) {
                moveToField(field);
                return true;
            } else if (this.isEnemy(field.getPiece())) {
                this.attack(field.getPiece());
                return true;
            } else {
                System.out.println("Field occupied by teammate piece.");
                return false;
            }
        } else {
            System.out.println("Move is not valid!");
            return false;
        }
    }

    public boolean isEnemy(Piece piece) {
        return !this.color.equals(piece.getColor());
    }

    protected void moveToField(Field fieldToMoveTo) {
        Field fieldToMoveFrom = this.getField();
        fieldToMoveFrom.setPiece(null);

        fieldToMoveTo.setPiece(this);
        this.setField(fieldToMoveTo);
    }

    @Override
    public void render() {
        String colorLetter = this.color.equals(Color.WHITE)? "W" : "B";
        System.out.print(colorLetter + this.id);
    }

    public void attack(Piece pieceToAttack) {
        Field field = pieceToAttack.getField();
        pieceToAttack.kill();
        this.moveToField(field);
    }

    public void kill() {
        if (this.field != null) {
            this.field.setPiece(null);
            this.field = null;
        }
    }

    public boolean isMoveValid(Field field) {
        if (this.field.equals(field)) {
            System.out.println("Can not move piece to the same spot!");
            return false;
        }

        return true;
    }
}
