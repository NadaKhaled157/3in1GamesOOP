package TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameHandler implements ActionListener {
    boolean isXTurn = true;
    GameGUI GUI;
    Board gameBoard;
    String winner;

    GameHandler(GameGUI GUI,Board gameBoard){
        this.GUI=GUI;
        this.gameBoard=gameBoard;
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                GUI.buttons[x][y].addActionListener(this);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (e.getSource() == GUI.buttons[x][y]) {
                    GUI.buttons[x][y].setFont(new Font("Arial", Font.BOLD, 50));
                    GUI.buttons[x][y].setFocusable(false);
                    if (!gameBoard.tiles[x][y].hasMark) {
                        if (isXTurn) {
                            GUI.buttons[x][y].setText("X");
                            gameBoard.tiles[x][y].setMark(x,y, MarkType.X);
                        } else {
                            GUI.buttons[x][y].setText("O");
                            gameBoard.tiles[x][y].setMark(x,y, MarkType.O);
                        }
                        gameBoard.tiles[x][y].hasMark=true;
                        isXTurn = !isXTurn;
                    }
                }
                gameUpdates();
            }
        }
    }
    public void gameUpdates() {
        if (gameBoard.isBoardFull())
            JOptionPane.showMessageDialog(null, "GAME OVER. DRAW.");
        if (gameBoard.isGameOver()) {
            winner = gameBoard.winner.toString();
            JOptionPane.showMessageDialog(GUI.mainFrame, "GAME OVER. " + winner + " WINS!");
        }
    }

    public static void main(String[] args) {
        GameGUI GUI= new GameGUI();
        Board gameBoard= new Board();
        new GameHandler(GUI, gameBoard);

    }
}









//    if(Panel.isPanelFull){
//        Panel.isGameOver;
//    }

