package main.java.com.chessmaster.piece;

import main.java.com.chessmaster.manager.Field;

public interface Blastable {
    int SCORE = 10;

    boolean blast(Field field);
}
