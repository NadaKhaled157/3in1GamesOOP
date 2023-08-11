package Chessmate;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameTimer {
    Timer timer;
    int minutes;
    int seconds;
    GameTimer(){
//        minutes = chessGameInfo.timerSlider.getValue();
        minutes=5;
        seconds=0;
        //startTimer(chessGUI);
    }

    public void startTimer(ChessGUI chessGUI) {
        timer =new Timer(1000, e -> {
            seconds--;
            if (seconds < 10 && minutes < 10) {
                chessGUI.blackPlayerTimer.setText("0" + minutes + ":0" + seconds);
            } else if (seconds < 10) chessGUI.blackPlayerTimer.setText(minutes + ":0" + seconds);
            else if (minutes < 10) chessGUI.blackPlayerTimer.setText("0" + minutes + ":" + seconds);
            else chessGUI.blackPlayerTimer.setText(minutes + ":" + seconds);
            if (seconds == -1) {
                seconds = 59;
                minutes--;
                if (minutes<10) chessGUI.blackPlayerTimer.setText("0" + minutes + ":" + seconds);
                else chessGUI.blackPlayerTimer.setText(minutes + ":" + seconds);
            }
            if (minutes == 0 && seconds == 0) {
                timer.stop();

            }
        });
//        timer =new Timer(1000, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                minutes -= 1;
//                seconds = 60;
//                while (minutes > 0 && seconds >= 0) {
//                    seconds--;
//                    if (seconds < 0) {
//                        minutes--;
//                        seconds = 60;
//                    }
//                    if (seconds < 10) {
//                        chessGUI.blackPlayerTimer.setText(minutes + ":0" + seconds);
//                        System.out.println(minutes + ":0" + seconds);
//                    } else {
//                        chessGUI.blackPlayerTimer.setText(minutes + ":0" + seconds);
//                        System.out.println(minutes + ":" + seconds);
//                    }
//                }
//                while (seconds >= 0 && minutes == 0) {
//                    if (seconds < 10) {
//                        chessGUI.blackPlayerTimer.setText(minutes + ":0" + seconds);
//                        System.out.println(minutes + ":0" + seconds);
//                    } else {
//                        chessGUI.blackPlayerTimer.setText(minutes + ":0" + seconds);
//                        System.out.println(minutes + ":" + seconds);
//                    }
//                    seconds--;
//                    if (seconds < 0) {
//                        break;
//                    }
//                }
//            }
//        });
    }



    public static void main (String[]args){
//        GameTimer testTimer= new GameTimer();

    }
}
