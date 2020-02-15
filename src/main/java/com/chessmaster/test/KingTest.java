package main.java.com.chessmaster.test;

import main.java.com.chessmaster.piece.King;
import main.java.com.chessmaster.config.Color;
import main.java.com.chessmaster.manager.Field;
import main.java.com.chessmaster.piece.Piece;

@SuppressWarnings("ALL")
public class KingTest {

    public static void testIfPieceCanMoveOneField() {
        System.out.print("testIfPieceCanMoveOneField -> ");

        Piece testElement = new King(Color.BLACK, new Field(1, 1));

        boolean isValid = testElement.isMoveValid(new Field(1, 2));

        String testMessage = (isValid) ? "Valid" : "Fail";
        System.out.println(testMessage);
    }

    public static void testIfPieceCanMoveMoreThanTwoFields(){
        System.out.print("testIfPieceCanMoveMoreThanTwoFields -> ");

        Piece testElement = new King(Color.BLACK, new Field(1, 1));

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
