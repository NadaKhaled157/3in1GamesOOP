package Chessmate;

public class Launcher {
    //    Tile[][] board = new Tile[8][8];
    public static void main(String[] args) {
        ChessGUI chessGUI = new ChessGUI();
        //Board chessboard = new Board();
        //Border blueBorder = BorderFactory.createLineBorder(Color.BLUE, 4);
        new ChessHandler(chessGUI);
    }
}
