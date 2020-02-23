package main.java.com.chessmaster.piece;

import main.java.com.chessmaster.config.Color;
import main.java.com.chessmaster.manager.Field;
import main.java.com.chessmaster.manager.GameBoard;
import main.java.com.chessmaster.manager.GameInfo;
import main.java.com.chessmaster.manager.Rendable;

@SuppressWarnings("ALL")
public abstract class Piece implements Rendable {
    private String color;
    private int power;
    private int id;
    private Field field;
    private String iconName;
    protected GameInfo gameInfo;
    protected GameBoard gameBoard;

    protected Piece(String color, Field field, int power, int id) {
        this.color = color;
        this.field = field;
        this.power = power;
        this.id = id;
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public String getColor() {
        return color;
    }

    public int getPower() {
        return power;
    }

    public int getId() {
        return id;
    }

    protected void setPower(int power) {
        this.power = power;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public boolean move(Field field) {
        if (isMoveValid(field)) {
            if (field.isEmpty()) {
                moveToField(field);
                return true;
            } else {
                this.attack(field.getPiece());
                return true;
            }
        } else {
            if (gameInfo != null) {
                gameInfo.addMsg("Move is not valid!");
            } else {
                System.out.println("Move is not valid!");
            }

            return false;
        }
    }

    public boolean isEnemy(Piece piece) {
        return !this.color.equals(piece.getColor());
    }

    protected void moveToField(Field fieldToMoveTo) {
        Field fieldToMoveFrom = this.getField();
        fieldToMoveFrom.setPiece(null);

        fieldToMoveTo.setPiece(this);
        this.setField(fieldToMoveTo);
    }

    @Override
    public void render() {
        String colorLetter = this.color.equals(Color.WHITE) ? "W" : "B";
        System.out.print(colorLetter + this.id);
    }

    public void attack(Piece pieceToAttack) {
        Field field = pieceToAttack.getField();
        pieceToAttack.kill();
        this.moveToField(field);
    }

    public void kill() {
        if (this.field != null) {
            this.field.setPiece(null);
            this.field = null;
        }
    }

    public boolean isMoveValid(Field field) {
        if (this.field.equals(field)) {
            if (gameInfo != null) {
                gameInfo.addMsg("Can not move piece to the same spot");
            } else {
                System.out.println("Can not move piece to the same spot");
            }

            return false;
        }

        if (!field.isEmpty() && !this.isEnemy(field.getPiece())) {
            if (gameInfo != null) {
                gameInfo.addMsg("Field occupied by teammate piece.");
            } else {
                System.out.println("Field occupied by teammate piece.");
            }

            return false;
        }

        return true;
    }

    public String getIconPath() {
        return "./main/resources/" + iconName + ".png";
    }

    protected void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public void setGameInfo(GameInfo gameInfo) {
        this.gameInfo = gameInfo;
    }
}
