package com.chessmaster;

import com.chessmaster.config.Color;
import com.chessmaster.dto.PieceStartPosition;
import com.chessmaster.manager.Field;
import com.chessmaster.manager.GameBoard;
import com.chessmaster.manager.GameEngine;
import com.chessmaster.piece.Piece;
import com.chessmaster.piece.PieceFactory;
import com.chessmaster.player.Player;
import com.chessmaster.test.BishopTest;
import com.chessmaster.test.KingTest;
import com.chessmaster.test.KnightTest;
import com.chessmaster.test.PawnTest;
import com.chessmaster.test.QueenTest;
import com.chessmaster.test.RookTest;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        PawnTest.run();
        BishopTest.run();
        QueenTest.run();
        KingTest.run();
        RookTest.run();
        KnightTest.run();

        GameBoard gameBoard = new GameBoard(8);
        List<Player> players = new ArrayList<>();
        players.add(new Player(Color.WHITE));
        players.add(new Player(Color.BLACK));

        for (PieceStartPosition pieceStartPosition : generatePieceList()) {
            Field field = gameBoard.getFieldByPosition(pieceStartPosition.getXPosition(), pieceStartPosition.getYPosition());
            if (field == null) {
                System.out.println(pieceStartPosition.getColor() + " piece with id " + pieceStartPosition.getPieceId() + " not added.Field position invalid.");
            } else {
                Piece piece = PieceFactory.createPiece(pieceStartPosition.getColor(), pieceStartPosition.getPieceId(), field);
                if (piece == null) {
                    System.out.println(pieceStartPosition.getColor() + " piece with id " + pieceStartPosition.getPieceId() + " not added.No such piece");
                }
                field.setPiece(piece);
            }
        }

        GameEngine gameEngine = new GameEngine(gameBoard, players);
        gameEngine.run();
    }

    private static List<PieceStartPosition> generatePieceList() {
        List<PieceStartPosition> pieceStartPositions = new ArrayList<>();
        pieceStartPositions.add(new PieceStartPosition(Color.WHITE, 1, 0, 1));
        pieceStartPositions.add(new PieceStartPosition(Color.WHITE, 1, 1, 1));
        pieceStartPositions.add(new PieceStartPosition(Color.WHITE, 1, 2, 1));
        pieceStartPositions.add(new PieceStartPosition(Color.WHITE, 7, 3, 1));
        pieceStartPositions.add(new PieceStartPosition(Color.WHITE, 7, 4, 1));
        pieceStartPositions.add(new PieceStartPosition(Color.WHITE, 1, 5, 1));
        pieceStartPositions.add(new PieceStartPosition(Color.WHITE, 1, 6, 1));
        pieceStartPositions.add(new PieceStartPosition(Color.WHITE, 1, 7, 1));

        pieceStartPositions.add(new PieceStartPosition(Color.WHITE, 4, 0, 0));
        pieceStartPositions.add(new PieceStartPosition(Color.WHITE, 3, 1, 0));
        pieceStartPositions.add(new PieceStartPosition(Color.WHITE, 2, 2, 0));
        pieceStartPositions.add(new PieceStartPosition(Color.WHITE, 5, 3, 0));
        pieceStartPositions.add(new PieceStartPosition(Color.WHITE, 6, 4, 0));
        pieceStartPositions.add(new PieceStartPosition(Color.WHITE, 2, 5, 0));
        pieceStartPositions.add(new PieceStartPosition(Color.WHITE, 3, 6, 0));
        pieceStartPositions.add(new PieceStartPosition(Color.WHITE, 4, 7, 0));


        pieceStartPositions.add(new PieceStartPosition(Color.BLACK, 1, 0, 6));
        pieceStartPositions.add(new PieceStartPosition(Color.BLACK, 1, 1, 6));
        pieceStartPositions.add(new PieceStartPosition(Color.BLACK, 1, 2, 6));
        pieceStartPositions.add(new PieceStartPosition(Color.BLACK, 7, 3, 6));
        pieceStartPositions.add(new PieceStartPosition(Color.BLACK, 7, 4, 6));
        pieceStartPositions.add(new PieceStartPosition(Color.BLACK, 1, 5, 6));
        pieceStartPositions.add(new PieceStartPosition(Color.BLACK, 1, 6, 6));
        pieceStartPositions.add(new PieceStartPosition(Color.BLACK, 1, 7, 6));

        pieceStartPositions.add(new PieceStartPosition(Color.BLACK, 4, 0, 7));
        pieceStartPositions.add(new PieceStartPosition(Color.BLACK, 3, 1, 7));
        pieceStartPositions.add(new PieceStartPosition(Color.BLACK, 2, 2, 7));
        pieceStartPositions.add(new PieceStartPosition(Color.BLACK, 6, 3, 7));
        pieceStartPositions.add(new PieceStartPosition(Color.BLACK, 5, 4, 7));
        pieceStartPositions.add(new PieceStartPosition(Color.BLACK, 2, 5, 7));
        pieceStartPositions.add(new PieceStartPosition(Color.BLACK, 3, 6, 7));
        pieceStartPositions.add(new PieceStartPosition(Color.BLACK, 4, 7, 7));

        return pieceStartPositions;
    }
}
