package Chessmate;

import java.util.ArrayList;

public abstract class Piece {
    PieceType pieceType;
    PieceColor pieceColor;
    TileID tileID;
    int x;
    int y;
    ArrayList<Tile> possibleMoves = new ArrayList<>();
    ArrayList<Tile> illegalMoves= new ArrayList<>();

    public void setX(int x){this.x=x;}
    public int getX(){return this.x;}
    public int getY(){return this.y;}
    public void setY(int y){this.y=y;}
    public abstract ArrayList<Tile> findPossibleMoves(Board currentBoard);
//    public abstract  ArrayList<Tile> findIllegalMoves(Board currentState, int x, int y);

//    public ArrayList<Tile> clearPossibleMoves(){
//        this.possibleMoves.clear();
//        return this.possibleMoves;
//    }
//    public ArrayList<Tile> clearIllegalMoves(){
//        this.illegalMoves.clear();
//        return this.illegalMoves;
//    }
}
