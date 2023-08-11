import Chessmate.ChessGUI;
import Chessmate.ChessHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

public class Main implements ActionListener {
    JFrame launchFrame = new JFrame();

    JLabel welcomeLabel = new JLabel("WELCOME TO CHESSMATE!");
    JButton chessButton = new JButton("Chess");
    //JButton thirdGame = new JButton("Tic Tac Toe");
    JButton xoButton = new JButton("Tic Tac Toe");
    JButton loginButton = new JButton("Login");
    JButton signupButton = new JButton("Signup");
    ImageIcon gameLogo = new ImageIcon("resources/Game Logo.png");
    JLabel gameLogoLabel= new JLabel();
    JLabel gameNameLabel=new JLabel("OOP GAMES");

    Font titleFont= new Font("Showcard Gothic",Font.BOLD,35);

    Main() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToFrame();
        addActionEvent();
        gameLogoLabel.setIcon(gameLogo);
        launchFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        launchFrame.setVisible(true);
    }

    public void setLayoutManager() {
        launchFrame.setLayout(null);
        launchFrame.setTitle("Welcome to OOP Games!");
        launchFrame.getContentPane().setBackground(ChessGUI.whitePlayerColor);
        chessButton.setFocusable(false);
//        thirdGame.setFocusable(false);
        xoButton.setFocusable(false);
        loginButton.setFocusable(false);
        signupButton.setFocusable(false);

        chessButton.setBackground(ChessGUI.blackPlayerColor);
//        thirdGame.setBackground(Color.DARK_GRAY);
        xoButton.setBackground(ChessGUI.blackPlayerColor);
        loginButton.setBackground(ChessGUI.blackPlayerColor);
        signupButton.setBackground(ChessGUI.blackPlayerColor);

        gameNameLabel.setForeground(ChessGUI.blackPlayerColor);
        chessButton.setForeground(ChessGUI.whitePlayerColor);
//        thirdGame.setBackground(Color.DARK_GRAY);
        xoButton.setForeground(ChessGUI.whitePlayerColor);
        loginButton.setForeground(ChessGUI.whitePlayerColor);
        signupButton.setForeground(ChessGUI.whitePlayerColor);

        gameNameLabel.setFont(titleFont);
        chessButton.setFont(ChessGUI.font15);
        xoButton.setFont(ChessGUI.font15);
        loginButton.setFont(ChessGUI.font15);
        signupButton.setFont(ChessGUI.font15);


    }

    public void setLocationAndSize() {
        launchFrame.setSize(500, 500);
        launchFrame.setLocationRelativeTo(null);
        welcomeLabel.setBounds(150, 60, 200, 40);
        gameLogoLabel.setBounds(80,10,150,150);
        gameNameLabel.setBounds(190,40,200,100);
        chessButton.setBounds(165, 200, 150, 30);
        //thirdGame.setBounds(165, 250, 150, 30);
        xoButton.setBounds(165, 300, 150, 30);
        loginButton.setBounds(165, 350, 150, 30);
        signupButton.setBounds(165, 400, 150, 30);
    }

    public void addComponentsToFrame() {
        // launchFrame.add(welcomeLabel);
        launchFrame.add(chessButton);
//        launchFrame.add(thirdGame);
        launchFrame.add(xoButton);
        launchFrame.add(loginButton);
        launchFrame.add(signupButton);
        launchFrame.add(gameLogoLabel);
        launchFrame.add(gameNameLabel);
        ImageIcon logo = new ImageIcon("resources/Game Logo.png");
        launchFrame.setIconImage(logo.getImage());
    }

    public void addActionEvent() {
        chessButton.addActionListener(this);
//        thirdGame.addActionListener(this);
        xoButton.addActionListener(this);
        loginButton.addActionListener(this);
        signupButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == chessButton) {
            launchFrame.dispose();
            new Chessmate.chessGameInfo();
        }
//        if (e.getSource() == thirdGame) {
//            launchFrame.dispose();
//            new GameInfo();
//        }
        if (e.getSource() == xoButton) {
            launchFrame.dispose();
            //new GameInfo();
        }
        if (e.getSource() == loginButton) {
            launchFrame.dispose();
            new LoginPage();
        }
        if (e.getSource() == signupButton) {
            launchFrame.dispose();
            new SignupPage();
        }
    }

    public static void main(String[]args){
        new Main();
    }
}

