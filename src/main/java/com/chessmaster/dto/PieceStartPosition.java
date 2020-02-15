package main.java.com.chessmaster.dto;

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

    public int getXPosition() {
        return xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    public String getColor() {
        return color;
    }
}
