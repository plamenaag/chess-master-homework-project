package com.chessmaster.test;

import com.chessmaster.config.PieceColor;
import com.chessmaster.manager.Field;
import com.chessmaster.piece.King;
import com.chessmaster.piece.Piece;

@SuppressWarnings("ALL")
public class KingTest {

    public static void testIfPieceCanMoveOneField() {
        System.out.print("testIfPieceCanMoveOneField -> ");

        Piece testElement = new King(PieceColor.BLACK, new Field(1, 1));

        boolean isValid = testElement.isMoveValid(new Field(1, 2));

        String testMessage = (isValid) ? "Valid" : "Fail";
        System.out.println(testMessage);
    }

    public static void testIfPieceCanMoveMoreThanTwoFields(){
        System.out.print("testIfPieceCanMoveMoreThanTwoFields -> ");

        Piece testElement = new King(PieceColor.BLACK, new Field(1, 1));

        boolean isValid = testElement.isMoveValid(new Field(1, 6));

        String testMessage = (isValid) ? "Valid" : "Fail";
        System.out.println(testMessage);
    }

    public static void run() {
        System.out.println("Start King tests");
        testIfPieceCanMoveOneField();
        testIfPieceCanMoveMoreThanTwoFields();
        System.out.println("End King tests");
    }
}
