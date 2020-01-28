package com.chessmaster.manager;

import java.util.ArrayList;
import java.util.List;

public class GameBoard implements Rendable {
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
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                fields.add(new Field(x, y));
            }
        }
    }

    public Field getFieldByPosition(int xPos, int yPos) {
        return fields.stream().filter(field -> field.getXPosition() == xPos && field.getYPosition() == yPos).findFirst().orElse(null);
    }

    @Override
    public void render() {
        printXs(sizeX);
        for (int y = 0; y < sizeY; y++) {
            printLine(sizeX);
            System.out.print(y+"|");
            for (int x = 0; x < sizeX; x++) {
                Field field = getFieldByPosition(x, y);
                field.render();
                System.out.print("|");
            }
            System.out.print(y);
            System.out.println();
        }
        printXs(sizeX);
    }

    private void printLine(int size) {
        System.out.print(" ");
        for (int i = 0; i < size; i++) {
            System.out.print("---");
        }
        System.out.print("-");
        System.out.println();
    }

    private void printXs(int size) {
        System.out.print(" ");
        for (int i = 0; i < size; i++) {
            System.out.print(" " + i + " ");
        }
        System.out.println();
    }
}
