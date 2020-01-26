package com.chessmaster.test;

import com.chessmaster.config.Color;
import com.chessmaster.manager.Field;
import com.chessmaster.piece.Piece;
import com.chessmaster.piece.Queen;

@SuppressWarnings("ALL")
public class QueenTest {
    public static void testIfPieceCanMoveVertically() {
        System.out.print("testIfPieceCanMoveVertically -> ");

        Piece testElement = new Queen(Color.BLACK, new Field(1, 1));

        boolean isValid = testElement.isMoveValid(new Field(1, 6));

        String testMessage = (isValid) ? "Valid" : "Fail";
        System.out.println(testMessage);
    }

    public static void testIfPieceCanMoveDiagonally(){
        System.out.print("testIfPieceCanMoveDiagonally -> ");

        Piece testElement = new Queen(Color.BLACK, new Field(1, 1));

        boolean isValid = testElement.isMoveValid(new Field(6, 6));

        String testMessage = (isValid) ? "Valid" : "Fail";
        System.out.println(testMessage);
    }

    public static void run() {
        System.out.println("Start Queen tests");
        testIfPieceCanMoveVertically();
        testIfPieceCanMoveDiagonally();
        System.out.println("End Queen tests");
    }
}
