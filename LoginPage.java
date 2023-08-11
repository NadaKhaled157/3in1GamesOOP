import Chessmate.ChessGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame implements ActionListener {

    JFrame loginPage = new JFrame();
    JLabel userLabel = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    protected static JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");
    JButton backButton = new JButton("BACK");
    JCheckBox showPassword = new JCheckBox("Show Password");
    ImageIcon logo = new ImageIcon("resources/pieces/black king.png");

    protected static String loggedInUser;
    //This is declared here because we need it in User class to read and write data
    //in the logged-in user's file

    LoginPage() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToFrame();
        addActionEvent();
        loginPage.setLocationRelativeTo(null);



    }

    public void setLayoutManager() {
        loginPage.setLayout(null);

        loginPage.setIconImage(logo.getImage());
        loginPage.setTitle("Login Page");
        loginPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginPage.setResizable(false);
        loginPage.getContentPane().setBackground(ChessGUI.whitePlayerColor);
        loginPage.setVisible(true);

        userLabel.setFont(ChessGUI.font12);
        passwordLabel.setFont(ChessGUI.font12);
        showPassword.setFont(ChessGUI.font12);
        loginButton.setFont(ChessGUI.font12);
        backButton.setFont(ChessGUI.font12);

        userLabel.setForeground(ChessGUI.blackPlayerColor);
        passwordLabel.setForeground(ChessGUI.blackPlayerColor);
        showPassword.setForeground(ChessGUI.blackPlayerColor);
        loginButton.setBackground(ChessGUI.blackPlayerColor);
        loginButton.setForeground(ChessGUI.whitePlayerColor);
        backButton.setBackground(ChessGUI.blackPlayerColor);
        backButton.setForeground(ChessGUI.whitePlayerColor);

        showPassword.setBackground(null);

    }

    public void setLocationAndSize() {
        loginPage.setBounds(10, 10, 350, 350);
        userLabel.setBounds(40, 70, 100, 30);
        passwordLabel.setBounds(40, 140, 100, 30);
        userTextField.setBounds(140, 70, 150, 30);
        passwordField.setBounds(140, 140, 150, 30);
        showPassword.setBounds(140, 170, 150, 30);
        loginButton.setBounds(40, 220, 100, 30);
        backButton.setBounds(190, 220, 100, 30);
    }

    public void addComponentsToFrame() {
        loginPage.add(userLabel);
        loginPage.add(passwordLabel);
        loginPage.add(userTextField);
        loginPage.add(passwordField);
        loginPage.add(showPassword);
        loginPage.add(loginButton);
        loginPage.add(backButton);
    }

    public void addActionEvent() {
        loginButton.addActionListener(this);
        backButton.addActionListener(this);
        showPassword.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String userText = userTextField.getText(); //username entered by user
            String pwdText = passwordField.getText(); //password entered by user
            if (User.LogInData(userText,pwdText)) {
                loggedInUser=userText;
                JOptionPane.showMessageDialog(this, "Login Successful");
                loginPage.dispose();
                //new LoggedIn();
            } else JOptionPane.showMessageDialog(this, "Invalid Username or Password");
        }
        if (e.getSource() == backButton) {
            loginPage.dispose();
            new Main();
        }
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('•');
            }
        }
    }
}
