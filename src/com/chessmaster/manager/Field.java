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

        if (field instanceof Field) {
            return false;
        }

        if (((Field) field).getXPosition() == this.getXPosition() &&
                ((Field) field).getYPosition() == this.getYPosition()) {
            return true;
        }

        return false;
    }
}
