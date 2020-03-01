package main.java.com.chessmaster.player;

public class Player {
    private String color;
    private int turnCount;
    private int score;

    public Player(String color) {
        this.color = color;
        this.setTurnCount(0);
        this.setScore(0);
    }

    public String getColor() {
        return color;
    }

    public int getTurnCount() {
        return turnCount;
    }

    public void setTurnCount(int turnCount) {
        this.turnCount = turnCount;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
