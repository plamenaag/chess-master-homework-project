package com.chessmaster.piece;

import com.chessmaster.manager.Field;

public interface Blastable {
    int SCORE = 10;

    boolean blast(Field field);
}
