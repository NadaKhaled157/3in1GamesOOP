package Chessmate;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class ChessGUI {
    JFrame mainFrame = new JFrame("CHESSMATE");
    JLabel backgroundLabel= new JLabel();
    JPanel[][] tiles= new JPanel[8][8];
    //JLabel[][]background=new JLabel[8][8];
    JButton[][] buttons= new JButton[8][8];
    JButton startGame= new JButton("Start Game");
    Board chessboard= new Board();

    Border darkRedBorder = BorderFactory.createLineBorder(new Color(139,0,0), 6);

    JFrame promotionFrame= new JFrame("Pawn Promotion");
    JFrame checkmateFrame;
    JButton queenButton;
    JButton rookButton;
    JButton bishopButton;
    JButton knightButton;

    JLabel blackPlayer= new JLabel("Black Player");
    JLabel whitePlayer= new JLabel("White Player");
    JLabel blackPlayerTimer= new JLabel("5:00");
    JLabel whitePlayerTimer= new JLabel("5:00");
    JPanel blackDeadPieces= new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel whiteDeadPieces=new JPanel(new FlowLayout(FlowLayout.LEFT));

    JLabel[] whiteDeadPiecesLabels= new JLabel[15];
    JLabel[] blackDeadPiecesLabels= new JLabel[15];

    public static Color bluePlayerColor= new Color(131,158,225);
    public static Color whitePlayerColor = new Color(249,244,241);

//    Color bluePlayerColor= new Color(45, 78, 105);
//    Color whitePlayerColor = new Color(255, 221, 196);

    ImageIcon whiteWood = new ImageIcon("resources/Chess Photos/white wood.jpg");
    ImageIcon darkWood = new ImageIcon("resources/Chess Photos/dark wood.jpg");

    //Pieces Images
    ImageIcon WP = new ImageIcon("resources/Chess Photos/Chess Pieces/White Pawn.png");
    ImageIcon WB = new ImageIcon("resources/Chess Photos/Chess Pieces/White Bishop.png");
    ImageIcon WK = new ImageIcon("resources/Chess Photos/Chess Pieces/White Knight.png");
    ImageIcon WR = new ImageIcon("resources/Chess Photos/Chess Pieces/White Rook.png");
    ImageIcon WQ = new ImageIcon("resources/Chess Photos/Chess Pieces/White Queen.png");
    ImageIcon WKing = new ImageIcon("resources/Chess Photos/Chess Pieces/White King.png");

    ImageIcon BP = new ImageIcon("resources/Chess Photos/Chess Pieces/Black Pawn.png");
    ImageIcon BB = new ImageIcon("resources/Chess Photos/Chess Pieces/Black Bishop.png");
    ImageIcon BK = new ImageIcon("resources/Chess Photos/Chess Pieces/Black Knight.png");
    ImageIcon BR = new ImageIcon("resources/Chess Photos/Chess Pieces/Black Rook.png");
    ImageIcon BQ = new ImageIcon("resources/Chess Photos/Chess Pieces/Black Queen.png");
    ImageIcon BKing = new ImageIcon("resources/Chess Photos/Chess Pieces/Black King.png");

    //Dead Pieces Images
//    ImageIcon DWP = new ImageIcon("resources/Photos/Chess Pieces/Dead White Pawn.png");
//    ImageIcon DWK = new ImageIcon("resources/Photos/Chess Pieces/Dead White Knight.png");

    ImageIcon backgroundImage= new ImageIcon("resources/Chess Photos/background.jpg");
    ImageIcon frameIcon = new ImageIcon("resources/Chess Photos/Chess Icon.png");




    public ChessGUI() {
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(null);
        mainFrame.setSize(700,850);
        mainFrame.setIconImage(frameIcon.getImage());
        // mainFrame.getContentPane().setBackground(new Color(132, 64, 18, 160));
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);


        blackPlayer.setBounds(50,0,100,50);
        whitePlayer.setBounds(50,715 ,100,50);
        blackPlayerTimer.setBounds(490,0,100,50);
        whitePlayerTimer.setBounds(490,715 ,100,50);
        blackDeadPieces.setBounds(55,35,400,35);
        whiteDeadPieces.setBounds(55,750,400,35);
        blackDeadPieces.setBackground(bluePlayerColor);
        whiteDeadPieces.setBackground(whitePlayerColor);

        backgroundLabel.setIcon(backgroundImage);
        backgroundLabel.setBounds(0,0,700,850);

        mainFrame.add(blackPlayer);
        mainFrame.add(whitePlayer);
        mainFrame.add(blackPlayerTimer);
        mainFrame.add(whitePlayerTimer);
        mainFrame.add(blackDeadPieces);
        mainFrame.add(whiteDeadPieces);
        //mainFrame.add(backgroundLabel);

        for (int i=0;i<15;i++){
            whiteDeadPiecesLabels[i] = new JLabel();
//            whiteDeadPiecesLabels[i].setSize(30,30);
            whiteDeadPiecesLabels[i].setIcon(null);
            whiteDeadPieces.add(whiteDeadPiecesLabels[i]);

            blackDeadPiecesLabels[i] = new JLabel();
//            blackDeadPiecesLabels[i].setSize(30,30);
            blackDeadPiecesLabels[i].setIcon(null);
            blackDeadPieces.add(blackDeadPiecesLabels[i]);
        }
//        whiteDeadPiecesLabels[0].setIcon(DWP);
//        whiteDeadPiecesLabels[1].setIcon(DWK);
//        whiteDeadPiecesLabels[2].setIcon(DWP);
//        whiteDeadPiecesLabels[3].setIcon(DWP);
//        whiteDeadPiecesLabels[4].setIcon(DWP);
//        whiteDeadPiecesLabels[5].setIcon(DWP);
//        whiteDeadPiecesLabels[6].setIcon(DWP);
//        whiteDeadPiecesLabels[7].setIcon(DWP);
//        whiteDeadPiecesLabels[8].setIcon(DWP);
//        whiteDeadPiecesLabels[9].setIcon(DWP);
//        whiteDeadPiecesLabels[10].setIcon(DWP);
//        whiteDeadPiecesLabels[11].setIcon(DWP);
//        whiteDeadPiecesLabels[12].setIcon(DWP);
//        whiteDeadPiecesLabels[13].setIcon(DWP);
//        whiteDeadPiecesLabels[14].setIcon(DWP);


        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                tiles[x][y] = new JPanel();
                //background[x][y]= new JLabel();
                buttons[x][y]= new JButton();
                //tiles[x][y].add(background[x][y]);
                if (y%2==0) {
                    if (x % 2 == 0)
                        tiles[x][y].setBackground(bluePlayerColor);
                        //background[x][y].setIcon(darkWood);
                    else {
                        tiles[x][y].setBackground(whitePlayerColor);
                        //background[x][y].setIcon(whiteWood);
                    }
                } else {
                    if ((x % 2)!= 0)
                        tiles[x][y].setBackground(bluePlayerColor);
                        //background[x][y].setIcon(darkWood);
                    else {
                        tiles[x][y].setBackground(whitePlayerColor);
                        //background[x][y].setIcon(whiteWood);
                    }
                }
                tiles[x][y].setBorder(BorderFactory.createLineBorder(Color.white));
                Dimension dimension = new Dimension(80, 80);
                tiles[x][y].setPreferredSize(dimension);
                buttons[x][y].setPreferredSize(new Dimension(80, 75));
                buttons[x][y].setOpaque(false);
                buttons[x][y].setBorderPainted(false);
                buttons[x][y].setBackground(Color.DARK_GRAY);
                buttons[x][y].setForeground(Color.white);
                tiles[x][y].setBounds(20 + (x * 80), 645 - (y * 80), 80, 80);
                //buttons[x][y].setBounds(35 + (x * 70), 560 - (y * 70), 70, 70);
                tiles[x][y].add(buttons[x][y]);
            }
        }

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                mainFrame.add(tiles[x][y]);
            }
        }
        startGame.setBounds(700,700,100,50);

        //mainFrame.add(startGame);
//        startGame.addActionListener(this);
        initializeBoardGUI();
        //boardGUI();
        //testBoardGUI();
        //testKingBoardGUI();
    }

    public void initializeBoardGUI(){

        for(int x=0;x<8;x++) {
            buttons[x][1].setIcon(WP);
        }
        buttons[0][0].setIcon(WR);
        buttons[7][0].setIcon(WR);
        buttons[1][0].setIcon(WK);
        buttons[6][0].setIcon(WK);
        buttons[2][0].setIcon(WB);
        buttons[5][0].setIcon(WB);
        buttons[3][0].setIcon(WQ);
        buttons[4][0].setIcon(WKing);

        for(int x=0;x<8;x++) {
            buttons[x][6].setIcon(BP);
        }
        buttons[0][7].setIcon(BR);
        buttons[7][7].setIcon(BR);
        buttons[1][7].setIcon(BK);
        buttons[6][7].setIcon(BK);
        buttons[2][7].setIcon(BB);
        buttons[5][7].setIcon(BB);
        buttons[3][7].setIcon(BQ);
        buttons[4][7].setIcon(BKing);

    }

    public void testKingBoardGUI(){
        buttons[1][4].setIcon(WR);
        buttons[2][2].setIcon(WKing);
        buttons[3][4].setIcon(WP);
        buttons[7][6].setIcon(WP);
        buttons[5][2].setIcon(BP);
        buttons[6][3].setIcon(WB);
        buttons[7][3].setIcon(WK);
        //buttons[1][5].setIcon(BK);
        buttons[2][6].setIcon(BKing);
        buttons[2][7].setIcon(BR);
    }

    public void boardGUI(){
        for (int x = 0; x < 8; x++) {
            String whitePawn = "White\nPawn";
            buttons[x][1].setText("<html>" + whitePawn.replaceAll("\\n", "<br>") + "</html>");
        }
        String whiteBishop="White\nBishop";
        buttons[2][0].setText("<html>" + whiteBishop.replaceAll("\\n", "<br>") + "</html>");
        buttons[5][0].setText("<html>" + whiteBishop.replaceAll("\\n", "<br>") + "</html>");
        String whiteRook="White\nRook";
        buttons[0][0].setText("<html>" + whiteRook.replaceAll("\\n", "<br>") + "</html>");
        buttons[7][0].setText("<html>" + whiteRook.replaceAll("\\n", "<br>") + "</html>");
        String whiteKing="White\nKing";
        buttons[3][0].setText("<html>" + whiteKing.replaceAll("\\n", "<br>") + "</html>");

        for (int x = 0; x < 8; x++) {
            String blackPawn = "Black\nPawn";
            buttons[x][6].setText("<html>" + blackPawn.replaceAll("\\n", "<br>") + "</html>");
        }
        String blackBishop="Black\nBishop";
        buttons[2][7].setText("<html>" + blackBishop.replaceAll("\\n", "<br>") + "</html>");
        buttons[5][7].setText("<html>" + blackBishop.replaceAll("\\n", "<br>") + "</html>");
        String blackRook="Black\nRook";
        buttons[0][7].setText("<html>" + blackRook.replaceAll("\\n", "<br>") + "</html>");
        buttons[7][7].setText("<html>" + blackRook.replaceAll("\\n", "<br>") + "</html>");
        String blackKing="Black\nKing";
        buttons[3][7].setText("<html>" + blackKing.replaceAll("\\n", "<br>") + "</html>");
    }

    public void setAndRemoveHighlight(Piece sourceSelected, int previousX, int previousY) {
        //Highlight possible moves when a source piece is selected
        Border greenBorder = BorderFactory.createLineBorder(Color.GREEN, 4);
        Border redBorder = BorderFactory.createLineBorder(Color.RED, 4);
        for (int i = 0; i < sourceSelected.possibleMoves.size(); i++) {
            if (!chessboard.boardTiles[sourceSelected.possibleMoves.get(i).x][sourceSelected.possibleMoves.get(i).y].isHighlighted) {
                this.tiles[sourceSelected.possibleMoves.get(i).x][sourceSelected.possibleMoves.get(i).y].setBorder(greenBorder);
                chessboard.boardTiles[sourceSelected.possibleMoves.get(i).x][sourceSelected.possibleMoves.get(i).y].isHighlighted = true;
            } else {
                this.tiles[sourceSelected.possibleMoves.get(i).x][sourceSelected.possibleMoves.get(i).y].setBorder(null);
                chessboard.boardTiles[sourceSelected.possibleMoves.get(i).x][sourceSelected.possibleMoves.get(i).y].isHighlighted = false;
            }
        }
        for (int i = 0; i < sourceSelected.illegalMoves.size(); i++) {
            if (!chessboard.boardTiles[sourceSelected.illegalMoves.get(i).x][sourceSelected.illegalMoves.get(i).y].isHighlighted) {
                this.tiles[sourceSelected.illegalMoves.get(i).x][sourceSelected.illegalMoves.get(i).y].setBorder(redBorder);
                chessboard.boardTiles[sourceSelected.illegalMoves.get(i).x][sourceSelected.illegalMoves.get(i).y].isHighlighted = true;
            } else {
                this.tiles[sourceSelected.illegalMoves.get(i).x][sourceSelected.illegalMoves.get(i).y].setBorder(null);
                chessboard.boardTiles[sourceSelected.illegalMoves.get(i).x][sourceSelected.illegalMoves.get(i).y].isHighlighted = false;
            }

//            } else {
//                this.tiles[sourceSelected.getX()][sourceSelected.getY()].setBorder(darkRedBorder);
            //chessboard.boardTiles[sourceSelected.getX()][sourceSelected.getY()].isHighlighted=false;
        }

        if (chessboard.boardTiles[previousX][previousY].getPiece()==null){
            this.tiles[previousX][previousY].setBorder(null);
            chessboard.boardTiles[previousX][previousY].isHighlighted=false;
        }
    }

    public void disableButtons(Piece destinationSelected) {
        // When a piece is selected, enable buttons of all its possible moves
        // and the selected piece button to allow the player to deselect it

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                this.buttons[x][y].setEnabled(false);
                if (chessboard.boardTiles[x][y].getPiece()!=null) {
//                    Icon pieceImage=this.buttons[x][y].getIcon();
                    Piece piece = chessboard.boardTiles[x][y].getPiece();
                    ImageIcon pieceIcon = getImageIcon(piece);
                    setDisabledIcons(pieceIcon,x,y);
                }
            }
        }
        this.buttons[destinationSelected.x][destinationSelected.y].setEnabled(true);
        for (int i = 0; i < destinationSelected.possibleMoves.size(); i++)
            this.buttons[destinationSelected.possibleMoves.get(i).x][destinationSelected.possibleMoves.get(i).y].setEnabled(true);
    }

    public void setDisabledIcons(ImageIcon pieceIcon, int x, int y){
        //This function sets an icon for disabled buttons, with the opacity
        //of the pieces' images reduced to indicate inactivity of buttons
        Image img = pieceIcon.getImage();
                    MediaTracker tracker = new MediaTracker(new JPanel());
                    tracker.addImage(img, 0);
                    try {
                        tracker.waitForAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
                    Graphics2D g2d = bi.createGraphics();
                    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f)); // set opacity level here
                    g2d.drawImage(img, 0, 0, null);
                    g2d.dispose();
                    this.buttons[x][y].setDisabledIcon(new ImageIcon(bi));
                }

    public void enableButtons() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                this.buttons[x][y].setEnabled(true);
            }
        }
    }

    public void deselectButton(Piece sourceSelected){
        //Deselecting a piece enables all buttons and
        //clears all highlights
        //as well as clear the selected piece's possible moves,
        //so it can be clicked again if the player wants,
        //because to select a piece, its possible moves and illegal moves lists
        //must be empty, otherwise program thinks you're deselecting
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                this.buttons[x][y].setEnabled(true);
                this.tiles[x][y].setBorder(null);
                chessboard.boardTiles[x][y].isHighlighted = false;
                sourceSelected.possibleMoves.clear();
                sourceSelected.illegalMoves.clear();
            }
        }
    }

    public void runKingInCheckGUI(Board currentBoard, Piece movedPiece, Player whitePlayer, Player blackPlayer){
        //movedPiece is the piece that put the king in check
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                this.buttons[x][y].setEnabled(false);
                //The next part fixes the image of movedPiece, so it's not greyed out
                //when the king is in check

                //Explanation: it's the only one greyed out bec all the other pieces were
                //previously disabled when the movedPiece was selected,
                //and when the movedPiece puts king in check, all buttons are disabled again
                //using the same disabled icon with low opacity, except movedPiece which doesn't have
                //one yet, so we make it here.

                ImageIcon pieceIcon = getImageIcon(movedPiece);
                setDisabledIcons(pieceIcon, movedPiece.getX(), movedPiece.getY());
            }
        }
        if (whitePlayer.isTurn){
            this.buttons[currentBoard.whiteKing.getX()][currentBoard.whiteKing.getY()].setEnabled(true);
            this.tiles[currentBoard.whiteKing.getX()][currentBoard.whiteKing.getY()].setBorder(darkRedBorder);
            //chessboard.boardTiles[currentBoard.whiteKing.getX()][currentBoard.whiteKing.getY()].isHighlighted = true;

        }
        if (blackPlayer.isTurn){
            this.buttons[currentBoard.blackKing.getX()][currentBoard.blackKing.getY()].setEnabled(true);
            this.tiles[currentBoard.blackKing.getX()][currentBoard.blackKing.getY()].setBorder(darkRedBorder);
            //chessboard.boardTiles[currentBoard.blackKing.getX()][currentBoard.blackKing.getY()].isHighlighted = true;
        }
    }

    private static ImageIcon getImageIcon(Piece movedPiece) {
        String filename="resources/Chess Photos/Chess Pieces/" + movedPiece.pieceColor + " " + movedPiece.pieceType + ".png";
        ImageIcon pieceIcon = new ImageIcon(filename);
        return pieceIcon;
    }

    public void setAndRemoveImage(Piece movedPiece, Piece eatenPiece, int previousX, int previousY){
        //Store moved piece image to move it to new location
        Icon movedPieceImage = this.buttons[previousX][previousY].getIcon();

        //Eaten Piece
        Icon eatenPieceImage;

        if (eatenPiece != null) {
            PieceColor eatenPieceColor=eatenPiece.pieceColor;
            PieceType eatenPieceType=eatenPiece.pieceType;
            eatenPieceImage= new ImageIcon("resources/Chess Photos/Chess Pieces/Dead "+eatenPieceColor+" "+eatenPieceType+".png");
//                eatenPieceImage = this.buttons[eatenPiece.x][eatenPiece.y].getIcon();
            if (movedPiece.pieceColor.equals(PieceColor.White)) {
                for (JLabel label : blackDeadPiecesLabels) {
                    if (label.getIcon() == null) {
                        label.setIcon(eatenPieceImage);
                        break;
                    }
                }
            } else {
                for (JLabel label : whiteDeadPiecesLabels) {
                    if (label.getIcon() == null) {
                        label.setIcon(eatenPieceImage);
                        break;
                    }
                }
            }
        }
        //Normal Movement
        this.buttons[movedPiece.x][movedPiece.y].setIcon(movedPieceImage);
        this.buttons[previousX][previousY].setIcon(null);
    }

    public void runPromotionGUI(PieceColor pieceColor){
        //promotionFrame= new JFrame("Pawn Promotion");

        bishopButton= new JButton();
        rookButton= new JButton();
        knightButton=new JButton();
        queenButton= new JButton();

        bishopButton.setBorderPainted(false);
        bishopButton.setBackground(bluePlayerColor);
        rookButton.setBackground(whitePlayerColor);
        knightButton.setBackground(whitePlayerColor);
        queenButton.setBackground(bluePlayerColor);

        ImageIcon promotionFrameIcon= new ImageIcon("resources/Chess Photos/Pawn Promotion.png");
        ImageIcon pawnPromotionLogo= new ImageIcon("resources/Chess Photos/promotion.png");

        if(pieceColor.equals(PieceColor.White)){
            bishopButton.setIcon(WB);
            rookButton.setIcon(WR);
            knightButton.setIcon(WK);
            queenButton.setIcon(WQ);
        } else {
            bishopButton.setIcon(BB);
            rookButton.setIcon(BR);
            knightButton.setIcon(BK);
            queenButton.setIcon(BQ);
        }


        promotionFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        promotionFrame.setLayout(null);
        promotionFrame.setSize(300,420);
        promotionFrame.setLocationRelativeTo(null);
        promotionFrame.setIconImage(promotionFrameIcon.getImage());
        promotionFrame.setVisible(true);

        JLabel promotionImage =new JLabel();
        promotionImage.setBounds(75,0,150,150);
        promotionImage.setIcon(pawnPromotionLogo);
        promotionFrame.add(promotionImage);

        bishopButton.setBounds(50,165,90,90);
        bishopButton.setFocusable(false);

        rookButton.setBounds(140,165,90,90);
        rookButton.setFocusable(false);

        knightButton.setBounds(50,255,90,90);
        knightButton.setFocusable(false);

        queenButton.setBounds(140,255,90,90);
        queenButton.setFocusable(false);

        promotionFrame.add(bishopButton);
        promotionFrame.add(rookButton);
        promotionFrame.add(knightButton);
        promotionFrame.add(queenButton);
    }

    public void runGameOverGUI(PieceColor pieceColor){
        checkmateFrame= new JFrame("GAME OVER");

//        ImageIcon gameOverFrameIcon= new ImageIcon("resources/Chess Photos/lose.png");
        ImageIcon gameOverLogo;
        JLabel checkmateImage =new JLabel();
        JLabel loserLabel=new JLabel();
        JLabel gameOverLabel=new JLabel("GAME OVER!");
        Font font=new Font("Cooper Black",Font.BOLD,25);

        if(pieceColor.equals(PieceColor.White)){
            checkmateFrame.getContentPane().setBackground(bluePlayerColor);
            gameOverLabel.setForeground(whitePlayerColor);
            loserLabel.setForeground(whitePlayerColor);
            gameOverLogo= new ImageIcon("resources/Chess Photos/White Lost.png");
            loserLabel.setText("WHITE LOST.");
        } else {
            checkmateFrame.getContentPane().setBackground(whitePlayerColor);
            gameOverLabel.setForeground(bluePlayerColor);
            loserLabel.setForeground(bluePlayerColor);
            gameOverLogo= new ImageIcon("resources/Chess Photos/Black Lost.png");
            loserLabel.setText("BLACK LOST.");
        }
        checkmateFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        checkmateFrame.setLayout(null);
        checkmateFrame.setSize(350,350);
        checkmateFrame.setLocationRelativeTo(null);
        checkmateFrame.setIconImage(frameIcon.getImage());

        checkmateFrame.setVisible(true);


        checkmateImage.setBounds(100,20,150,150);
        checkmateImage.setIcon(gameOverLogo);


        gameOverLabel.setBounds(75,190,200,50);
        loserLabel.setBounds(75,240,200,50);
        gameOverLabel.setFont(font);
        loserLabel.setFont(font);

        checkmateFrame.add(checkmateImage);
        checkmateFrame.add(gameOverLabel);
        checkmateFrame.add(loserLabel);

    }

//    public void setAndRemoveImage(Piece movedPiece){
//        JLabel deadPieceLabel= new JLabel();
//        if (movedPiece.pieceColor.equals(PieceColor.Black)){
//            deadPieceLabel.setText("W");
//            whiteDeadPieces.add(deadPieceLabel);
//        } else {
//            deadPieceLabel.setText("B");
//            blackDeadPieces.add(deadPieceLabel);
//        }
//    }

    //    public void testKingBoardGUI(){
//        String whiteKing="White\nKing";
//        buttons[2][2].setText("<html>" + whiteKing.replaceAll("\\n", "<br>") + "</html>");
//        String whiteRook="White\nRook";
//        buttons[1][4].setText("<html>" + whiteRook.replaceAll("\\n", "<br>") + "</html>");
//        String whitePawn = "White\nPawn";
//        buttons[3][4].setText("<html>" + whitePawn.replaceAll("\\n", "<br>") + "</html>");
//        String whiteBishop="White\nBishop";
//        buttons[4][7].setText("<html>" + whiteBishop.replaceAll("\\n", "<br>") + "</html>");
//        buttons[6][3].setText("<html>" + whiteBishop.replaceAll("\\n", "<br>") + "</html>");
//    }

//    public void testBoardGUI(){
//        String whitePawn = "White\nPawn";
//        buttons[4][5].setText("<html>" + whitePawn.replaceAll("\\n", "<br>") + "</html>");
//        String whiteKing="White\nKing";
//        buttons[3][0].setText("<html>" + whiteKing.replaceAll("\\n", "<br>") + "</html>");
//        String whiteRook="White\nRook";
//        buttons[3][4].setText("<html>" + whiteRook.replaceAll("\\n", "<br>") + "</html>");
//        String whiteBishop="White\nBishop";
//        buttons[5][0].setText("<html>" + whiteBishop.replaceAll("\\n", "<br>") + "</html>");
//        buttons[0][0].setText("<html>" + whiteRook.replaceAll("\\n", "<br>") + "</html>");
//
//        String blackPawn = "Black\nPawn";
//        buttons[4][2].setText("<html>" + blackPawn.replaceAll("\\n", "<br>") + "</html>");
//        String blackKing="Black\nKing";
//        buttons[3][7].setText("<html>" + blackKing.replaceAll("\\n", "<br>") + "</html>");
//        String blackRook="Black\nRook";
//        buttons[4][3].setText("<html>" + blackRook.replaceAll("\\n", "<br>") + "</html>");
//
//    }


    public static void main (String[]args) {
        ChessGUI chessGUI = new ChessGUI();
        //chessGUI.runPromotionGUI(PieceColor.White);
        chessGUI.runGameOverGUI(PieceColor.White);

    }
}
