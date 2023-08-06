package TicTacToe;

import javax.swing.*;
import java.awt.*;

public class xoGUI {
    JFrame mainFrame = new JFrame("Tic Tac Toe");
    JPanel[][] tiles= new JPanel[3][3];
    JButton[][] buttons= new JButton[3][3];



    xoGUI() {
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(null);
        mainFrame.setSize(650,650);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                tiles[x][y] = new JPanel();
                buttons[x][y]= new JButton();

                tiles[x][y].setBackground(Color.lightGray);
                tiles[x][y].setBorder(BorderFactory.createLineBorder(Color.white));
                Dimension dimension = new Dimension(100, 100);
                tiles[x][y].setPreferredSize(dimension);
                buttons[x][y].setPreferredSize(new Dimension(100, 100));
                buttons[x][y].setOpaque(false);
                buttons[x][y].setBorderPainted(false);
                buttons[x][y].setBackground(Color.DARK_GRAY);
                tiles[x][y].add(buttons[x][y]);
            }
        }
        tiles[0][0].setBounds(160, 220,100,100);
        tiles[0][1].setBounds(160, 120,100,100);
        tiles[0][2].setBounds(160, 20,100,100);

        tiles[1][0].setBounds(260, 220,100,100);
        tiles[1][1].setBounds(260, 120,100,100);
        tiles[1][2].setBounds(260, 20,100,100);

        tiles[2][0].setBounds(360, 220,100,100);
        tiles[2][1].setBounds(360, 120,100,100);
        tiles[2][2].setBounds(360, 20,100,100);

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                mainFrame.add(tiles[x][y]);
            }
        }
    }

    public static void main (String[]args){
        new xoGUI();
    }
}
