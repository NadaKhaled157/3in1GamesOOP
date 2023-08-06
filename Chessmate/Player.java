package Chessmate;

public class Player {
    boolean isTurn;
    PieceColor playerColor;

    Player(boolean isTurn, PieceColor playerColor){
        this.isTurn=isTurn;
        this.playerColor=playerColor;
    }
    public void changeTurn() {
        this.isTurn=!this.isTurn;
    }

}
