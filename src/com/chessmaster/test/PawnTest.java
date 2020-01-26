package com.chessmaster.test;

import com.chessmaster.config.PawnMoveDirection;
import com.chessmaster.config.Color;
import com.chessmaster.manager.Field;
import com.chessmaster.piece.Pawn;
import com.chessmaster.piece.Piece;

@SuppressWarnings("ALL")
public class PawnTest {

    public static void testIfPieceCanMoveOneField() {
        System.out.print("testIfPieceCanMoveOneField -> ");

        Piece testElement = new Pawn(Color.BLACK, new Field(1, 1), PawnMoveDirection.UP);

        boolean isValid = testElement.isMoveValid(new Field(1, 2));

        String testMessage = (isValid) ? "Valid" : "Fail";
        System.out.println(testMessage);
    }

    public static void testIfPieceCanMoveMoreThanTwoFields(){
        System.out.print("testIfPieceCanMoveMoreThanTwoFields -> ");

        Piece testElement = new Pawn(Color.BLACK, new Field(1, 1), PawnMoveDirection.UP);

        boolean isValid = testElement.isMoveValid(new Field(1, 6));

        String testMessage = (isValid) ? "Valid" : "Fail";
        System.out.println(testMessage);
    }

    public static void run() {
        System.out.println("Start Pawn tests");
        testIfPieceCanMoveOneField();
        testIfPieceCanMoveMoreThanTwoFields();
        System.out.println("End Pawn tests");
    }
}
