package com.chessmaster.piece;

import com.chessmaster.manager.Field;

public abstract class Piece {
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

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public void move(Field field) {
        if (isMoveValid(field)) {
            if (field.isEmpty()) {
                moveToField(field);
            } else if (this.isEnemy(field.getPiece())) {
                this.attack(field.getPiece());
            } else {
                System.out.println("Field occupied by teammate piece.");
                return;
            }
        } else {
            System.out.println("Move is not valid!");
        }
    }

    public boolean isEnemy(Piece piece) {
        return this.color.equals(piece.getColor());
    }

    protected void moveToField(Field fieldToMoveTo) {
        fieldToMoveTo.setPiece(this);
        this.setField(fieldToMoveTo);
    }

    public void render() {
        // implement later or make abstract
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
