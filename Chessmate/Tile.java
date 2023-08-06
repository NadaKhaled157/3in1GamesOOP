package Chessmate;

public class Tile {
    private Piece piece;
    boolean hasPiece=false;
    boolean isHighlighted=false;
    int x;
    int y;
    TileID tileID;
    Tile(int x, int y){
        this.x=x;
        this.y=y;
    }

    public void setX(int x){this.x=x;}
    public int getX(){return this.x;}
    public int getY(){return this.y;}
    public void setY(int y){this.y=y;}
    public void setPiece(Piece piece){this.piece=piece;}
    public Piece getPiece(){return this.piece;}

}
