package com.chessmaster.manager;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {
    private int sizeX;
    private int sizeY;
    private List<Field> fields;

    public GameBoard(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        initializeFields(sizeX, sizeY);
    }

    // this constructor is calling the 1st constructor
    // example: you want to create GameBoard 5x5
    // if you call GameBoard(5); - > this will call the
    // constructor above and pass him the value of 5 TWICE
    public GameBoard(int size) {
        this(size, size);
    }

    private void initializeFields(int sizeX, int sizeY) {
        this.fields = new ArrayList<>();
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; i < sizeY; i++) {
                fields.add(new Field(i, j));
            }
        }
    }

    public static void render() {

    }
}
