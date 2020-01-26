package com.chessmaster.dto;

public class PieceStartPosition {
    private int pieceId;
    private int xPosition;
    private int yPosition;
    private String color;

    public PieceStartPosition(String color, int pieceId, int xPosition, int yPosition) {
        this.color = color;
        this.pieceId = pieceId;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public int getPieceId() {
        return pieceId;
    }

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public String getColor() {
        return color;
    }
}
