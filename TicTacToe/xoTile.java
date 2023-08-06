package TicTacToe;

public class xoTile {
    boolean hasMark;
    MarkType markType;
    int x;
    int y;
    public void setMark(int x, int y, MarkType markType){this.markType=markType;}
    public MarkType getMark(xoTile tileNumber){return markType;}
    public void setX(int X) {
        this.x = x;
    }
    public int getX() {
        return x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getY() {return y;}


}

