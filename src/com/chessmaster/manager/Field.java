package com.chessmaster.manager;

import com.chessmaster.piece.Piece;

public class Field {
    private int xPosition;
    private int yPosition;
    private Piece piece;

    public Field(int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public boolean isEmpty() {
        return getPiece() == null;
    }

    public int getXPosition() {
        return xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    @Override
    public boolean equals(Object field) {
        if (field == null) {
            return false;
        }

        // this is for checking if the argument you pass to the function
        // is of type Field, because if it's not, there's no need to compare the fields in the 'if' below and cast them
        //as well
        if (field instanceof Field) {
            return false;
        }

        // this is for checking the fields positions, not their references
        if (((Field) field).getXPosition() == this.getXPosition() &&
                ((Field) field).getYPosition() == this.getYPosition()) {
            return true;
        }

        return false;
    }
}
