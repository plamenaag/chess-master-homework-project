package main.java.com.chessmaster.manager;

import main.java.com.chessmaster.config.Constants;
import main.java.com.chessmaster.piece.Blastable;
import main.java.com.chessmaster.piece.Piece;
import main.java.com.chessmaster.player.Player;
import main.java.com.chessmaster.config.Color;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("ALL")
public class GameEngine extends JFrame implements MouseListener {
    private Scanner scanner;
    private GameBoard gameBoard;
    private List<Player> players;
    private ScheduledExecutorService executor;
    private Player currentPlayer;
    private GameInfo gameInfo;
    private Field clickedFieldForSelectedPiece;
    private Field clickedFieldForSelectedFieldToMoveTo;
    private Piece selectedPiece;
    private Field selectedFieldToMoveTo;
    private String mouseBtnClicked;
    private List<Field> validMoves;

    public GameEngine(GameBoard gameBoard, List<Player> players, GameInfo gameInfo) {
        super();
        this.gameBoard = gameBoard;
        this.players = players;
        this.gameInfo = gameInfo;
        initializeScreen();
    }

    private void initializeScreen() {
        int screenWidth = 2 * Constants.BORDER_WIDTH
                + this.gameBoard.getSizeX() * Constants.BLOCK_SIZE
                + this.gameBoard.getSizeX() * 1
                + Constants.FRAME_SIZE * 2
                + 1;

        int screenHeight = 2 * Constants.BORDER_WIDTH
                + this.gameBoard.getSizeY() * Constants.BLOCK_SIZE
                + this.gameBoard.getSizeY() * 1
                + Constants.BAR_HEIGHT
                + 4 + Constants.GAME_INFO_DISPLAY_HEIGHT;

        this.setSize(screenWidth, screenHeight);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLayout(null);
        this.addMouseListener(this);
        this.setLocationRelativeTo(null);// center the dialog in the screen
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(Constants.BORDER_WIDTH,
                Constants.BORDER_WIDTH, Constants.BORDER_WIDTH, Constants.BORDER_WIDTH, java.awt.Color.RED));
    }

    public void run() {
        this.currentPlayer = null;
        this.selectedPiece = null;
        this.selectedFieldToMoveTo = null;
        executor = Executors.newScheduledThreadPool(1);
        this.runLoop();
    }

    public void runLoop() {
        Player nextPlayer = this.getNextPlayer();
        if (currentPlayer != nextPlayer) {
            this.clickedFieldForSelectedFieldToMoveTo = null;
            this.clickedFieldForSelectedPiece = null;
            this.selectedPiece = null;
            this.selectedFieldToMoveTo = null;
            currentPlayer = nextPlayer;
            gameInfo.addMsg((currentPlayer.getColor().equals(Color.WHITE) ? "White" : "Black") + " Player's move.");
        }

        this.selectedPiece = selectPiece(currentPlayer);
        if (selectedPiece == null) {
            this.clickedFieldForSelectedPiece = null;
            this.repaint();
            return;
        }

        validMoves = getPieceValidMoves(selectedPiece);

        this.selectedFieldToMoveTo = selectFieldToMoveTo();
        if (selectedFieldToMoveTo == null) {
            this.clickedFieldForSelectedFieldToMoveTo = null;
            this.repaint();
            return;
        }

        boolean validMove;

        Piece possibleKill = selectedFieldToMoveTo.getPiece();

        if (selectedPiece instanceof Blastable && this.mouseBtnClicked != null && this.mouseBtnClicked.equals("RIGHT_BTN")) {
            validMove = ((Blastable) selectedPiece).blast(selectedFieldToMoveTo);
        } else {
            validMove = selectedPiece.move(selectedFieldToMoveTo);
        }

        validMoves = null;

        if (validMove) {
            if (possibleKill != null) {
                currentPlayer.setScore(currentPlayer.getScore() + possibleKill.getPoints());
            }

            currentPlayer.setTurnCount(currentPlayer.getTurnCount() + 1);
            this.repaint();
            executor.schedule(this::runLoop, 500, TimeUnit.MILLISECONDS);
        } else {
            this.clickedFieldForSelectedPiece = null;
            this.clickedFieldForSelectedFieldToMoveTo = null;
            this.repaint();
            this.runLoop();
        }
    }

    private List<Field> getPieceValidMoves(Piece piece) {
        gameInfo.setSuppressAdd(true);
        List<Field> fields = new ArrayList<>();
        for (Field field : gameBoard.getFields()) {
            if (piece.isMoveValid(field)) {
                fields.add(field);
            }
        }
        gameInfo.setSuppressAdd(false);
        return fields;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Player whitePlayer = players.stream().filter(p -> p.getColor().equals(Color.WHITE)).findFirst().orElse(null);
        Player blackPlayer = players.stream().filter(p -> p.getColor().equals(Color.BLACK)).findFirst().orElse(null);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("White player score: " + whitePlayer.getScore(), 390, 65);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("Black player score: " + blackPlayer.getScore(), 390, 80);

        g.setFont(new Font("arial", Font.PLAIN, 14));
        int msgStartY = 45;
        for (String msg : gameInfo.getInfo()) {
            g.drawString(msg, 40, msgStartY);
            msgStartY += 15;
        }

        int lineY = Constants.BAR_HEIGHT + Constants.BORDER_WIDTH + Constants.GAME_INFO_DISPLAY_HEIGHT - 5;
        g.setColor(java.awt.Color.red);
        g.fillRect(0, lineY, this.getWidth(), 5);
        g.setColor(java.awt.Color.darkGray);

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        BufferedImage img = null;

        boolean isBlack = true;
        for (Field field : this.gameBoard.getFields()) {
            int x = field.getScreenXPosition();
            int y = field.getScreenYPosition();

            if (field.getXPosition() != 0) {
                if (isBlack) {
                    g.setColor(java.awt.Color.white);
                    isBlack = false;
                } else {
                    g.setColor(java.awt.Color.darkGray);
                    isBlack = true;
                }
            }

            java.awt.Color oldColor = g.getColor();
            if (this.clickedFieldForSelectedPiece != null && this.clickedFieldForSelectedPiece.equals(field)) {
                g.setColor(java.awt.Color.red);
            } else if (this.clickedFieldForSelectedFieldToMoveTo != null && this.clickedFieldForSelectedFieldToMoveTo.equals(field)) {
                g.setColor(java.awt.Color.blue);
            } else if (this.validMoves != null && validMoves.contains(field)) {
                g.setColor(java.awt.Color.green);
            }

            g.fillRect(x, y, Constants.BLOCK_SIZE, Constants.BLOCK_SIZE);

            g.setColor(oldColor);

            if (!field.isEmpty()) {
                try {
                    img = ImageIO.read(classloader.getResource(field.getPiece().getIconPath()));
                } catch (IOException e) {
                    img = null;
                }
                g.drawImage(img, x, y, Constants.BLOCK_SIZE, Constants.BLOCK_SIZE, this);
            }
        }
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
            if (this.clickedFieldForSelectedPiece == null) {
                gameInfo.addMsg("Choose piece");
                return null;
            }

            if (this.clickedFieldForSelectedPiece.isEmpty()) {
                gameInfo.addMsg("There is no piece on this field!");
                gameInfo.addMsg("Choose piece");
                return null;
            }

            if (this.clickedFieldForSelectedPiece.getPiece().getColor().equals(player.getColor())) {
                Piece piece = this.clickedFieldForSelectedPiece.getPiece();
                if (piece instanceof Blastable) {
                    gameInfo.addMsg("Press Left btn to move, Press Right btn to shoot");
                }
                return piece;
            } else {
                gameInfo.addMsg("Can not move enemy piece!");
                gameInfo.addMsg("Choose piece");
                return null;
            }
        } catch (Exception ex) {
            gameInfo.addMsg("Something went wrong.");
            gameInfo.addMsg("Choose piece");
            return null;
        }
    }

    private Field selectFieldToMoveTo() {
        try {
            if (this.clickedFieldForSelectedFieldToMoveTo == null) {
                gameInfo.addMsg("Choose field to move to");
                return null;
            }

            return this.clickedFieldForSelectedFieldToMoveTo;
        } catch (Exception ex) {
            gameInfo.addMsg("Something went wrong.");
            return null;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == 3) {
            this.mouseBtnClicked = "RIGHT_BTN";
        } else {
            this.mouseBtnClicked = "LEFT_BTN";
        }

        if (clickedFieldForSelectedPiece == null) {
            clickedFieldForSelectedPiece = getFieldByMouseClickPos(e.getX(), e.getY()); //set
        } else {
            clickedFieldForSelectedFieldToMoveTo = getFieldByMouseClickPos(e.getX(), e.getY()); // set
        }
        this.runLoop();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private Field getFieldByMouseClickPos(int clickedX, int clickedY) {

        for (Field field : gameBoard.getFields()) {
            if (field.getScreenXPosition() <= clickedX && clickedX <= field.getScreenXPosition() + Constants.BLOCK_SIZE
                    && field.getScreenYPosition() <= clickedY && clickedY <= field.getScreenYPosition() + Constants.BLOCK_SIZE) {
                return field;
            }
        }

        return null;
    }
}
