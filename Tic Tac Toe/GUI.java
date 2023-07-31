//import javax.swing.*;
//import java.awt.*;
//
//public class GUI {
//    JFrame mainFrame = new JFrame();
//    //JPanel gamePanel = new JPanel();
//    JPanel[][] tile = new JPanel[3][3];
//
//    GUI() {
//        setLayoutManager();
//        setLocationAndSize();
//        addComponentsToWindow();
//    }
//
//    public void setLayoutManager() {
//        mainFrame.setVisible(true);
//        mainFrame.setLayout(null);
//        for (int x = 0; x < 4; x++) {
//            for (int y = 0; y < 4; y++) {
//                tile[x][y].setBackground(Color.DARK_GRAY);
//            }
//        }
//        //gamePanel.setLayout(new java.awt.BorderLayout());
//        //gamePanel.setBackground(Color.DARK_GRAY);
//    }
//
//    public void setLocationAndSize() {
//        mainFrame.setSize(700, 700);
//        //gamePanel.setBounds(20, 20, 150, 150);
//        for (int x = 0; x < 4; x++) {
//            for (int y = 0; y < 4; y++) {
//                tile[x][y].setSize(100, 100);
//            }
//        }
//        tile[0][0].setLocation(20,20);
//        tile[0][1].setLocation(70,20);
//        tile[0][2].setLocation(120,20);
//        tile[1][0].setLocation(20,70);
//        tile[1][1].setLocation(70,70);
//        tile[1][2].setLocation(120,70);
//        tile[2][0].setLocation(20,120);
//        tile[2][1].setLocation(70,120);
//        tile[2][2].setLocation(120,120);
//    }
//
//        public void addComponentsToWindow () {
//        //mainFrame.add(gamePanel);
//            for (int x = 0; x < 4; x++) {
//                for (int y = 0; y < 4; y++) {
//                    mainFrame.add(tile[x][y]);
//                }
//            }
//        }
//
//        public static void main (String[]args){
//            new GUI();
//        }
//    }