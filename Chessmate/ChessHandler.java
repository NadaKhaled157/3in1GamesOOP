package Chessmate;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChessHandler implements ActionListener {
    ChessGUI chessGUI;
    Board chessboard;
    Piece selectedPiece;
    Piece movedPiece;
    Piece eatenPiece;
    Player whitePlayer= new Player(true,PieceColor.White);
    Player blackPlayer= new Player(false, PieceColor.Black);

    int previousX;
    int previousY;


    public ChessHandler(ChessGUI chessGUI) {
        this.chessGUI = chessGUI;
        this.chessboard = chessGUI.chessboard;
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                chessGUI.buttons[x][y].addActionListener(this);
            }
        }
        whitePlayer.timer= new GameTimer();
        whitePlayer.timer.startTimer(chessGUI);
        blackPlayer.timer=new GameTimer();
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (e.getSource() == chessGUI.buttons[x][y]) {
                    // Selected tile is not empty
                    if (chessboard.boardTiles[x][y].getPiece() != null) {
                        // Player selecting a piece to move
                        if ((isSourceSelected(whitePlayer.isTurn, whitePlayer.playerColor, chessboard.boardTiles[x][y].getPiece().pieceColor))
                                || (isSourceSelected(blackPlayer.isTurn, blackPlayer.playerColor, chessboard.boardTiles[x][y].getPiece().pieceColor))) {
                            selectedPiece = chessboard.boardTiles[x][y].getPiece();
                            //Player deselects a piece
                            if ((!selectedPiece.possibleMoves.isEmpty() || !selectedPiece.illegalMoves.isEmpty())) {
                                    if (selectedPiece instanceof King) {
                                        if (!((King) selectedPiece).isInCheck)
                                            chessGUI.deselectButton(selectedPiece);
                                    } else
                                chessGUI.deselectButton(selectedPiece);
                            } else {
                                //selectedPiece.possibleMoves.clear();
                                selectedPiece.findPossibleMoves(chessboard);
                                chessGUI.disableButtons(selectedPiece);
//                                if(blackPlayer.isTurn && chessboard.blackKing.threatPiece!=null && !(selectedPiece instanceof King)){
//                                    System.out.print("THREAT PIECE: "+chessboard.blackKing.threatPiece.pieceType+" ");
//                                    System.out.print("X"+chessboard.blackKing.threatPiece.getX());
//                                    System.out.println("Y"+chessboard.blackKing.threatPiece.getY());
//                                    Tile threatPieceTile = chessboard.boardTiles[chessboard.blackKing.threatPiece.getX()][chessboard.blackKing.threatPiece.getY()];
//                                    if (chessboard.blackKing.isInDanger) {
//                                        doNotEndangerKing(threatPieceTile, chessboard.blackKing.threatPiece);
//                                    }
////                                     else {
////                                        chessGUI.disableButtons(selectedPiece);
////                                    }
//                                }
                                if (selectedPiece instanceof King) {
                                    ((King) selectedPiece).wouldBeInDanger(chessboard);
                                    chessGUI.disableButtons(selectedPiece);
//                                    if(((King) selectedPiece).canCastle(chessboard)) {
//                                        if (chessboard.boardTiles[selectedPiece.getX() + 3][selectedPiece.getY()].getPiece() instanceof Rook rook)
//                                            if (rook.pieceColor.equals(selectedPiece.pieceColor))
//                                                System.out.println("CASTLING ALLOWED");
//                                    }
                                }
                                if (selectedPiece instanceof Pawn){
                                    if(((Pawn) selectedPiece).canPerformEnPassant(chessboard)){
                                        Piece threatenedPawn=((Pawn) selectedPiece).threatenedPawn;
                                        if(((Pawn) threatenedPawn).isFirstMove){
                                            selectedPiece.possibleMoves.add(chessboard.boardTiles[selectedPiece.getX() - 1][selectedPiece.getY() + 1]);
                                            System.out.println("En Passant Available");
                                        }
                                    }
                                }
                                System.out.println("--------------SELECTED PIECE INFO----------------");
                                System.out.println(chessboard.boardTiles[x][y].getPiece().pieceType+" X"+chessboard.boardTiles[x][y].getPiece().getX()+" Y"+
                                        chessboard.boardTiles[x][y].getPiece().getY());

                                // printing possible moves
                                System.out.println("Possible Moves: "+selectedPiece.possibleMoves.size());
                                for (int i = 0; i < selectedPiece.possibleMoves.size(); i++) {
                                    System.out.print("X" + selectedPiece.possibleMoves.get(i).x + " ");
                                    System.out.println("Y" + selectedPiece.possibleMoves.get(i).y);

                                }
                                //printing illegal moves
                                System.out.println("Illegal Moves: "+selectedPiece.illegalMoves.size());
                                for (int i = 0; i < selectedPiece.illegalMoves.size(); i++) {
                                    System.out.print("X" + selectedPiece.illegalMoves.get(i).x + " ");
                                    System.out.println("Y" + selectedPiece.illegalMoves.get(i).y);

                                }
                                System.out.println("---------------------------------------------------------");
                                //Highlighting and enabling possible moves of selected piece
                                chessGUI.setAndRemoveHighlight(selectedPiece, x, y);
//                                chessGUI.disableButtons(selectedPiece);
                                movedPiece = selectedPiece;
                            }
                        } else {
                            // Player selecting a piece out of turn
                            selectedPiece = chessboard.boardTiles[x][y].getPiece();
                            if (chessboard.boardTiles[x][y].hasPiece && !isAttackingOpponentPiece(x, y)) {
                                System.out.println("not your turn");
                                JOptionPane.showMessageDialog(null, "NOT YOUR TURN");
                            }
                        }
                    }
                    // Player selecting a destination for their piece
                    if (chessboard.boardTiles[x][y].isHighlighted) {
                        //Attacking opponent's piece
                        if (chessboard.boardTiles[x][y].hasPiece) {
                            System.out.println("reached before attack condition");
                            if (isAttackingOpponentPiece(x, y)) {
                                System.out.println("OPPONENT ATTACKED");
                                eatenPiece = chessboard.boardTiles[x][y].getPiece();
                                setAndRemovePiece(movedPiece, x, y);
                                chessGUI.setAndRemoveImage(movedPiece, eatenPiece, previousX, previousY);
                            }
                        } else {
                            setAndRemovePiece(movedPiece, x, y);
                            chessGUI.setAndRemoveImage(movedPiece, null, previousX, previousY);
                        }
                        //Moving to an empty tile
                        System.out.println(movedPiece.pieceType);

                        chessGUI.setAndRemoveHighlight(movedPiece, previousX, previousY);
                        chessGUI.enableButtons();
                        whitePlayer.changeTurn();
                        blackPlayer.changeTurn();
                        //Clear a piece's possible moves after it is moved
                        movedPiece.possibleMoves.clear();
                        movedPiece.illegalMoves.clear();
                        if (movedPiece instanceof Pawn){
                            if (((Pawn) movedPiece).canPromote(chessboard)) {
                            chessGUI.runPromotionGUI(movedPiece.pieceColor);
                            Action bishopPromotion = new AbstractAction() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    Bishop bishopPiece = new Bishop(movedPiece.x, movedPiece.y, movedPiece.pieceColor);
                                    handlePromotion(movedPiece, bishopPiece);
                                    System.out.println("BISHOP TEST");
                                    chessGUI.promotionFrame.dispose();
                                }
                            };
                            Action rookPromotion = new AbstractAction() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    Rook rookPiece = new Rook(movedPiece.x, movedPiece.y, movedPiece.pieceColor);
                                    handlePromotion(movedPiece, rookPiece);
                                    System.out.println("ROOK TEST");
                                    chessGUI.promotionFrame.dispose();
                                }
                            };

                            Action knightPromotion = new AbstractAction() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    Knight knightPiece = new Knight(movedPiece.x, movedPiece.y, movedPiece.pieceColor);
                                    handlePromotion(movedPiece, knightPiece);
                                    System.out.println("KNIGHT TEST");
                                    chessGUI.promotionFrame.dispose();
                                }
                            };
                            Action queenPromotion = new AbstractAction() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    Queen queenPiece = new Queen(movedPiece.x, movedPiece.y, movedPiece.pieceColor);
                                    handlePromotion(movedPiece, queenPiece);
                                    System.out.println("QUEEN TEST");
                                    chessGUI.promotionFrame.dispose();
                                }
                            };
                            chessGUI.bishopButton.addActionListener(bishopPromotion);
                            chessGUI.rookButton.addActionListener(rookPromotion);
                            chessGUI.knightButton.addActionListener(knightPromotion);
                            chessGUI.queenButton.addActionListener(queenPromotion);

                            System.out.println("PROMOTION");
                        }
                            if ((movedPiece.pieceColor.equals(PieceColor.White) && previousY==1) ||
                                    (movedPiece.pieceColor.equals(PieceColor.Black) && previousY==6))
                                ((Pawn) movedPiece).isFirstMove=true;
                            else
                                ((Pawn) movedPiece).isFirstMove=false;

                            if (chessboard.boardTiles[movedPiece.getX()][movedPiece.getY()]==((Pawn)selectedPiece).enPassantTile){
                                System.out.println("EN PASSANT SUCCESSFUL");

                                chessboard.boardTiles[((Pawn) selectedPiece).threatenedPawn.getX()][((Pawn) selectedPiece).threatenedPawn.getY()].setPiece(null);
                                chessboard.boardTiles[((Pawn) selectedPiece).threatenedPawn.getX()][((Pawn) selectedPiece).threatenedPawn.getY()].hasPiece=false;
                                System.out.println("PreviousX"+previousX);
                                System.out.println("PreviousY"+previousY);
                                chessGUI.setAndRemoveImage(null,((Pawn) selectedPiece).threatenedPawn,previousX,previousY);
                                chessGUI.buttons[((Pawn) selectedPiece).threatenedPawn.getX()][((Pawn) selectedPiece).threatenedPawn.getY()].setIcon(null);
//                                ((Pawn) movedPiece).capturedPieceTile.setPiece(null);
//                                ((Pawn) movedPiece).capturedPieceTile.hasPiece=false;
                            }

                    }
//                                chessboard.whiteKing.isInCheck(chessboard);
//                                chessboard.blackKing.isInCheck(chessboard);
//                        chessboard.whiteKing.isInCheck=false;
//                        chessboard.blackKing.isInCheck=false;
                        chessboard.whiteKing.wouldBeInDanger(chessboard);
                        chessboard.blackKing.wouldBeInDanger(chessboard);

                        if((chessboard.whiteKing.isInCheck(chessboard)&& whitePlayer.isTurn) ||
                                (chessboard.blackKing.isInCheck(chessboard)&& blackPlayer.isTurn)){
//                            chessboard.whiteKing.possibleMoves.clear();
//                            chessboard.blackKing.possibleMoves.clear();
//                            chessboard.whiteKing.findPossibleMoves(chessboard);
//                            chessboard.blackKing.findPossibleMoves(chessboard);
//                            chessboard.whiteKing.wouldBeInDanger(chessboard);
//                            chessboard.blackKing.wouldBeInDanger(chessboard);

                            chessGUI.runKingInCheckGUI(chessboard,movedPiece,whitePlayer,blackPlayer);
                            chessboard.whiteKing.isCheckmated(chessboard);
                            chessboard.blackKing.isCheckmated(chessboard);
//
                            System.out.println("White Checkmate: "+ chessboard.whiteKing.isCheckmated);
                            System.out.println("Black Checkmate: "+ chessboard.blackKing.isCheckmated);

                            if(chessboard.whiteKing.isCheckmated(chessboard)){
                                chessGUI.runGameOverGUI(PieceColor.White);
                                //JOptionPane.showMessageDialog(null, "BLACK WINS");
                            }
                            if (chessboard.blackKing.isCheckmated(chessboard)){
                                chessGUI.runGameOverGUI(PieceColor.Black);
//                                JOptionPane.showMessageDialog(null, "WHITE WINS");
                            }
                        }
//                        else if ((chessboard.whiteKing.isInCheck(chessboard)&& blackPlayer.isTurn)){
//                            chessGUI.runGameOverGUI(PieceColor.White);
//                        } else if ((chessboard.blackKing.isInCheck(chessboard)&& whitePlayer.isTurn)){
//                            chessGUI.runGameOverGUI(PieceColor.Black);
//                        }
                        System.out.println("White King: "+chessboard.whiteKing.isInCheck);
                        System.out.println("Black King: "+chessboard.blackKing.isInCheck);
                        //Clear all possible moves after checking both king's condition
                        //to allow for deselection to take place normally
                        //as it depends on the presence of possible and illegal moves
                        chessboard.whiteKing.possibleMoves.clear();
                        chessboard.whiteKing.illegalMoves.clear();
                        chessboard.blackKing.possibleMoves.clear();
                        chessboard.blackKing.illegalMoves.clear();

                        System.out.println("New Tile: " + chessboard.boardTiles[x][y].getPiece().pieceType);
                        System.out.println("Old Tile: " + chessboard.boardTiles[previousX][previousY].getPiece());
                    }
                }
            }
        }
    }

    public boolean isSourceSelected (boolean isTurn, PieceColor playerColor, PieceColor selectedPieceColor){
        return isTurn && playerColor.toString().equals(selectedPieceColor.toString());
    }

    public boolean isAttackingOpponentPiece(int x,int y){
        if (chessboard.boardTiles[x][y].isHighlighted)
            //white player selecting black piece or
            // black player selecting white piece
            return whitePlayer.isTurn && chessboard.boardTiles[x][y].getPiece().pieceColor.toString().equals(PieceColor.Black.toString())
                    || (blackPlayer.isTurn && chessboard.boardTiles[x][y].getPiece().pieceColor.toString().equals(PieceColor.White.toString()));
        return false;
    }

    public void setAndRemovePiece(Piece movedPiece, int x, int y){
        //removing piece from previous tile
        previousX= movedPiece.x;
        previousY=movedPiece.y;
        chessboard.boardTiles[previousX][previousY].setPiece(null);
        chessboard.boardTiles[previousX][previousY].hasPiece=false;
        chessGUI.buttons[movedPiece.x][movedPiece.y].setText("");

        //adding piece to new tile
        chessboard.boardTiles[x][y].setPiece(movedPiece);
        chessboard.boardTiles[x][y].hasPiece=true;
        movedPiece.setX(x);
        movedPiece.setY(y);
    }
    public void handlePromotion(Piece movedPiece, Piece promotedPiece){
        int newX= movedPiece.x;
        int newY= movedPiece.y;
        //this.chessGUI.chessboard.boardTiles[newX][newY].setPiece(null);
        this.chessGUI.chessboard.boardTiles[newX][newY].setPiece(promotedPiece);
        String promotedPieceLabel= promotedPiece.pieceColor +"\n"+promotedPiece.pieceType.toString();
        String filename= "resources/Photos/Chess Pieces/"+promotedPiece.pieceColor.toString()+" "+promotedPiece.pieceType.toString()+".png";
        System.out.println(filename);
        ImageIcon test=new ImageIcon(filename);
        this.chessGUI.buttons[newX][newY].setIcon(test);
        this.chessGUI.buttons[newX][newY].setText(null);
        //this.chessGUI.buttons[newX][newY].setText("<html>" + promotedPieceLabel.replaceAll("\\n", "<br>") + "</html>");
    }

    //This method is used to prevent a piece protecting the king from moving
    //and possibly putting the king in danger of being checkmated.
    //The piece is only allowed to move if it can eat the threatening piece
    public void doNotEndangerKing(Tile tile, Piece threatPiece){
        if(!selectedPiece.possibleMoves.contains(tile) &&
                threatPiece.possibleMoves.contains(chessboard.boardTiles[selectedPiece.getX()][selectedPiece.getY()])){
            chessGUI.enableButtons();
            selectedPiece.possibleMoves.clear();
        } else if(selectedPiece instanceof Rook || selectedPiece instanceof Queen){
            //selectedPiece.possibleMoves.clear();
            //UP
            for(int i=1;selectedPiece.getY()+i<=threatPiece.getY();i++){
                selectedPiece.possibleMoves.add(chessboard.boardTiles[selectedPiece.getX()][selectedPiece.getY()+i]);
            }
            //DOWN
            for(int i=1;selectedPiece.getY()-i>=threatPiece.getY();i++){
                selectedPiece.possibleMoves.add(chessboard.boardTiles[selectedPiece.getX()][selectedPiece.getY()-i]);
            }
            //RIGHT
            for(int i=1;selectedPiece.getX()+i<=threatPiece.getX();i++){
                selectedPiece.possibleMoves.add(chessboard.boardTiles[selectedPiece.getX()+i][selectedPiece.getY()]);
            }
            //LEFT
            for(int i=1;selectedPiece.getX()-i>=threatPiece.getX();i++){
                selectedPiece.possibleMoves.add(chessboard.boardTiles[selectedPiece.getX()-i][selectedPiece.getY()]);
            }
            chessGUI.disableButtons(selectedPiece);
        } else if(selectedPiece instanceof Bishop || selectedPiece instanceof Queen){
            //UP RIGHT
            int i=selectedPiece.getX()+1;
            int j=selectedPiece.getY()+1;
            while(i<=threatPiece.getX() && j<=threatPiece.getY()){
                selectedPiece.possibleMoves.add(chessboard.boardTiles[i][j]);
                i++;
                j++;
            }
            //UP LEFT
            i=selectedPiece.getX()-1;
            j=selectedPiece.getY()+1;
            while(i>=threatPiece.getX() && j<=threatPiece.getY()){
                selectedPiece.possibleMoves.add(chessboard.boardTiles[i][j]);
                i--;
                j++;
            }
            //DOWN RIGHT
            i=selectedPiece.getX()+1;
            j=selectedPiece.getY()-1;
            while(i<=threatPiece.getX() && j>=threatPiece.getY()){
                selectedPiece.possibleMoves.add(chessboard.boardTiles[i][j]);
                i++;
                j--;
            }
            //DOWN LEFT
            i=selectedPiece.getX()-1;
            j=selectedPiece.getY()-1;
            while(i>=threatPiece.getX() && j>=threatPiece.getY()){
                selectedPiece.possibleMoves.add(chessboard.boardTiles[i][j]);
                i--;
                j--;
            }
            chessGUI.disableButtons(selectedPiece);
        }
        else if (selectedPiece instanceof Knight) {
            selectedPiece.possibleMoves.clear();
            selectedPiece.possibleMoves.add(tile);
        }
    }
}
