package TicTacToe;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPage implements ActionListener {
    JFrame mainFrame = new JFrame("Start Page");
    JButton xoButton= new JButton("Tic Tac Toe");
    JButton chessButton= new JButton("Chess");
    JButton fourButton= new JButton("Four in Four");

    StartPage() {
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setLayout(null);
    mainFrame.setSize(400,550);
    mainFrame.setLocationRelativeTo(null);
    mainFrame.setVisible(true);

    xoButton.setBounds(115,200,150,50);
    xoButton.setFocusable(false);
    chessButton.setBounds(115,280,150,50);
    chessButton.setFocusable(false);
    fourButton.setBounds(115,360,150,50);
    fourButton.setFocusable(false);

    addComponentsToFrame();
    addActionEvent();
}

public void addComponentsToFrame(){
        mainFrame.add(xoButton);
        mainFrame.add(chessButton);
        mainFrame.add(fourButton);
}
public void addActionEvent(){
        xoButton.addActionListener(this);
}
public void actionPerformed(ActionEvent e){
        if (e.getSource()==xoButton){
            mainFrame.dispose();
            GameGUI GUI= new GameGUI();
            Board gameBoard= new Board();
            new GameHandler(GUI, gameBoard);
        }
}
public static void main (String[] args){
        new StartPage();
}
}
