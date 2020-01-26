package com.chessmaster.manager;

import com.chessmaster.config.Color;
import com.chessmaster.piece.Blastable;
import com.chessmaster.piece.Piece;
import com.chessmaster.player.Player;

import java.util.List;
import java.util.Scanner;

@SuppressWarnings("ALL")
public class GameEngine {
    private Scanner scanner;
    private GameBoard gameBoard;
    private List<Player> players;

    public GameEngine(GameBoard gameBoard, List<Player> players) {
        this.gameBoard = gameBoard;
        this.players = players;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        Player currentPlayer = null;
        do {
            gameBoard.render();
            currentPlayer = this.getNextPlayer();
            System.out.println((currentPlayer.getColor().equals(Color.WHITE)? "White" : "Black") + " Player's move");
            boolean validMove = false;
            do {
                Piece selectedPiece = null;
                Field fieldToMoveTo = null;

                do {
                    selectedPiece = selectPiece(currentPlayer);
                } while (selectedPiece == null);


                do {
                    fieldToMoveTo = selectFieldToMoveTo();
                } while (fieldToMoveTo == null);

                if(selectedPiece instanceof Blastable){
                    System.out.print("Do you want to blast (y/n)?");
                    String answer = scanner.nextLine();
                    if(answer.equals("y")){
                        validMove = ((Blastable) selectedPiece).blast(fieldToMoveTo);
                    }else{
                        validMove = selectedPiece.move(fieldToMoveTo);
                    }
                }else{
                    validMove = selectedPiece.move(fieldToMoveTo);
                }



            } while (validMove == false);

            currentPlayer.setTurnCount(currentPlayer.getTurnCount() + 1);
        } while (true);
    }

    private Player getNextPlayer() {
        Player player1 = players.get(0);
        Player player2 = players.get(1);
        if (player1.getTurnCount() == 0 && player2.getTurnCount() == 0) {
            if (player1.getColor().equals(Color.WHITE)) {
                return player1;
            } else {
                return player2;
            }
        } else if (player1.getTurnCount() > player2.getTurnCount()) {
            return player2;
        } else {
            return player1;
        }
    }

    private Piece selectPiece(Player player) {
        try {
            System.out.print("Choose piece by position x and y:");
            String[] positions = scanner.nextLine().split(" ");
            int positionX = Integer.parseInt(positions[0]);
            int positionY = Integer.parseInt(positions[1]);

            Field field = this.gameBoard.getFieldByPosition(positionX, positionY);
            if (field == null) {
                System.out.println("Wrong field positions.");
                return null;
            }

            if (field.isEmpty()) {
                System.out.println("There is no piece on this field!");
                return null;
            }

            if (field.getPiece().getColor().equals(player.getColor())) {
                return field.getPiece();
            } else {
                System.out.println("Can not move enemy piece!");
                return null;
            }

        } catch (Exception ex) {
            System.out.println("Something went wrong.");
            return null;
        }
    }

    private Field selectFieldToMoveTo() {
        try {
            System.out.print("Choose field to move to by x and y:");
            String[] positions = scanner.nextLine().split(" ");
            int positionX = Integer.parseInt(positions[0]);
            int positionY = Integer.parseInt(positions[1]);

            Field field = this.gameBoard.getFieldByPosition(positionX, positionY);
            if (field == null) {
                System.out.println("Wrong field positions.");
                return null;
            }

            return field;

        } catch (Exception ex) {
            System.out.println("Something went wrong.");
            return null;
        }
    }
}
