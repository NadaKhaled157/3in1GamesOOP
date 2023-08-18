package Chessmate;

import java.util.ArrayList;

public class King extends Piece{

    //ArrayList<Tile> allPossibleMoves= new ArrayList<>(); //possible moves of all other pieces on board
    ArrayList<Piece> threatPieces= new ArrayList<>();
    boolean isInDanger;
    Piece threatPiece;
    boolean isInCheck;
    boolean isCheckmated;

    boolean isFirstMove=true; //Used in castling
    boolean canShortCastle;
    boolean canLongCastle;


    King(int x, int y, PieceColor pieceColor) {
        this.x = x;
        this.y = y;
        this.pieceType = PieceType.King;
        this.pieceColor = pieceColor;

    }

    @Override
    public ArrayList<Tile> findPossibleMoves(Board currentBoard) {

        int[] X= {this.x-1,this.x-1,this.x-1,this.x,this.x+1,this.x+1,this.x+1,this.x};
        int[] Y={this.y-1,this.y,this.y+1,this.y+1,this.y+1,this.y,this.y-1,this.y-1};

        for(int i=0;i<X.length;i++) {
            if (X[i] < 0 || X[i] > 7 || Y[i] < 0 || Y[i] > 7) {
                continue;
            }
            if (currentBoard.boardTiles[X[i]][Y[i]].hasPiece) {
                if (!currentBoard.boardTiles[X[i]][Y[i]].getPiece().pieceColor.equals(this.pieceColor))
                    this.possibleMoves.add(currentBoard.boardTiles[X[i]][Y[i]]);
                else
                    this.illegalMoves.add(currentBoard.boardTiles[X[i]][Y[i]]);
            } else
                this.possibleMoves.add(currentBoard.boardTiles[X[i]][Y[i]]);
        }
//        System.out.println("KING'S POSSIBLE BEFORE IN DANGER: ");
//        for (int i=0;i<this.possibleMoves.size();i++)
//            System.out.println("X"+this.possibleMoves.get(i).x+" Y"+this.possibleMoves.get(i).y);

        //wouldBeInDanger(currentBoard);
        //isInCheck = isInCheck(currentBoard);
        return this.possibleMoves;

    }

    public void canCastle(Board currentBoard) {
        if ((this.pieceColor.equals(PieceColor.Black) && this.isFirstMove) || (this.pieceColor.equals(PieceColor.White) && this.isFirstMove)) {
            if (this.pieceColor.equals(PieceColor.Black)) {
                currentBoard.findAllPossibleMovesOfOpponent(PieceColor.White);
            }
            if (this.pieceColor.equals(PieceColor.White)) {
                currentBoard.findAllPossibleMovesOfOpponent(PieceColor.Black);
            }
            //Short Castling
            if (this.x+3<7 && this.x+2<7 && this.x+1<7){
            if (currentBoard.boardTiles[this.x + 3][this.y].hasPiece &&
                    currentBoard.boardTiles[this.x + 3][this.y].getPiece() instanceof Rook && currentBoard.boardTiles[this.x + 3][this.y].getPiece().pieceColor.equals(this.pieceColor)) { //1. King and rook haven't moved
                if (!currentBoard.boardTiles[this.x + 1][this.y].hasPiece && !currentBoard.boardTiles[this.x + 2][this.y].hasPiece) {//2. Tiles between king and rook are empty
                    if (!currentBoard.allPossibleMoves.contains(currentBoard.boardTiles[this.x + 2][this.y]) &&
                            this.possibleMoves.contains(currentBoard.boardTiles[this.x + 1][this.y])) //3. Tiles between king and rook would not put king in check
                        if (!this.isInCheck) //4. King is not in check
                            canShortCastle = true;
                }
            }
        }
            //Long Castling
            if (this.x - 4>0 && this.x - 3>0 && this.x - 2>0 && this.x - 1>0){
                if (currentBoard.boardTiles[this.x - 4][this.y].hasPiece &&
                        currentBoard.boardTiles[this.x - 4][this.y].getPiece() instanceof Rook && currentBoard.boardTiles[this.x - 4][this.y].getPiece().pieceColor.equals(this.pieceColor)) { //1. King and rook haven't moved
                    if (!currentBoard.boardTiles[this.x - 1][this.y].hasPiece && !currentBoard.boardTiles[this.x - 2][this.y].hasPiece &&
                            !currentBoard.boardTiles[this.x - 3][this.y].hasPiece) {//2. Tiles between king and rook are empty
                        if (!currentBoard.allPossibleMoves.contains(currentBoard.boardTiles[this.x - 2][this.y]) &&
                                !currentBoard.allPossibleMoves.contains(currentBoard.boardTiles[this.x - 3][this.y]) &&
                                this.possibleMoves.contains(currentBoard.boardTiles[this.x - 1][this.y])) //3. Tiles between king and rook would not put king in check
                            if (!this.isInCheck) //4. King is not in check
                                canLongCastle = true;
                    }
                }
        }
        } else {
            canShortCastle = false;
            canLongCastle = false;
        }
    }
    public void wouldBeInDanger(Board currentBoard) {
        //This method doesn't allow the king to move in
        //a position where it would be in check and therefor checkmate-d
//        System.out.println("---------------ALL PIECES POSSIBLE MOVES----------------");

        if (this.pieceColor.equals(PieceColor.White)) {
            currentBoard.findAllPossibleMovesOfOpponent(PieceColor.Black);
        } else {
            currentBoard.findAllPossibleMovesOfOpponent(PieceColor.White);
        }

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Tile currentTile = currentBoard.boardTiles[x][y];
                //PAWN DANGER
                if (currentTile.getPiece()!=null && !currentTile.getPiece().pieceColor.equals(this.pieceColor)) {
                    if (currentTile.getPiece() instanceof Pawn pawn) {
                        //remove in front of pawn from possible threatening moves
                        //because pawn cannot eat in front of it
                        pawn.wouldEndangerKing(currentBoard);
                        Tile removedTile;
                        if (pawn.pieceColor.equals(PieceColor.White)) {
                            removedTile = currentBoard.boardTiles[pawn.getX()][pawn.getY() + 1];
                        } else {
                            removedTile = currentBoard.boardTiles[pawn.getX()][pawn.getY() - 1];
                        }
//                        System.out.println("removed X" + removedTile.x + " removed Y" + removedTile.y);
                        currentBoard.allPossibleMoves.remove(removedTile);
                    }
                }
            }
        }
        for(int i=0;i<8;i++) {
            //Y-AXIS
            Tile currentTile = currentBoard.boardTiles[this.x][i];
            if (currentTile.hasPiece &&
                    !(currentTile.getPiece().pieceColor.equals(this.pieceColor))) {
                if (currentTile.getPiece() instanceof Rook || currentTile.getPiece() instanceof Queen) {
                    Piece rookQueen = currentTile.getPiece();
                    this.threatPieces.add(rookQueen);
                    System.out.println("THREAT PIECE TEST: " + rookQueen.pieceType);
                    int k = 0;
                    while (rookQueen.getY() + k < 7 && rookQueen.getY() - k > 0) {
                        //ABOVE ROOK
                        for (k = 1; rookQueen.getY() + k < 7; k++) {
                            Tile checkedTile = currentBoard.boardTiles[rookQueen.getX()][rookQueen.getY() + k];
                            if (testIfPieceThreatensKing(rookQueen, checkedTile)) break;
                        }
                        //BELOW ROOK
                        for (k = 1; rookQueen.getY() - k > 0; k++) {
                            Tile checkedTile = currentBoard.boardTiles[rookQueen.getX()][rookQueen.getY() - k];
                            if (testIfPieceThreatensKing(rookQueen, checkedTile)) break;
                        }
                        System.out.println("Threat Piece Possible Moves: " + rookQueen.possibleMoves.size());
                        for (k = 0; k < rookQueen.possibleMoves.size(); k++) {
                            System.out.print("X" + rookQueen.possibleMoves.get(k).x + " ");
                            System.out.println("Y" + rookQueen.possibleMoves.get(k).y);

                        }
                    }
                    currentBoard.allPossibleMoves.addAll(rookQueen.possibleMoves);
                    currentTile.getPiece().possibleMoves.clear();
                }
            }
            //X-AXIS
            currentTile = currentBoard.boardTiles[i][this.y];
            if (currentTile.hasPiece &&
                    !(currentTile.getPiece().pieceColor.equals(this.pieceColor))) {
                if (currentTile.getPiece() instanceof Rook || currentTile.getPiece() instanceof Queen) {
                    Piece rookQueen = currentTile.getPiece();
                    this.threatPieces.add(rookQueen);
                    int k=0;
                    while (rookQueen.getX() + k < 7 && rookQueen.getX() - k > 0) {
                        //RIGHT OF ROOK
                        for (k = 1; rookQueen.getY() + k < 7; k++) {
                            Tile checkedTile = currentBoard.boardTiles[rookQueen.getX() + k][rookQueen.getY()];
                            if (testIfPieceThreatensKing(rookQueen, checkedTile)) break;
                        }
                        //LEFT OF ROOK
                        for (k = 1; rookQueen.getY() - k > 0; k++) {
                            Tile checkedTile = currentBoard.boardTiles[rookQueen.getX() - k][rookQueen.getY()];
                            if (testIfPieceThreatensKing(rookQueen, checkedTile)) break;
                        }
//                    System.out.println("Threat Piece Possible Moves: " + rookQueen.possibleMoves.size());
//                    for (int k = 0; k < rookQueen.possibleMoves.size(); k++) {
//                        System.out.print("X" + rookQueen.possibleMoves.get(k).x + " ");
//                        System.out.println("Y" + rookQueen.possibleMoves.get(k).y);
//                    }
                    }
                    currentBoard.allPossibleMoves.addAll(rookQueen.possibleMoves);
                    currentTile.getPiece().possibleMoves.clear();
                }
            }
            //UP-RIGHT LOWER-LEFT DIAGONAL
            if (this.x + i < 8 && this.y + i < 8) {
                //If there's a bishop up-right of king, check it's lower-left moves
                currentTile = currentBoard.boardTiles[this.x + i][this.y + i];
                if (currentTile.hasPiece &&
                        !(currentTile.getPiece().pieceColor.equals(this.pieceColor))) {
                    if (currentTile.getPiece() instanceof Bishop || currentTile.getPiece() instanceof Queen) {
                        Piece bishopQueen = currentTile.getPiece();
                        this.threatPieces.add(bishopQueen);
                        int k=1;
                        while(bishopQueen.getX()-k>0 && bishopQueen.getY()-k>0){
                            Tile checkedTile= currentBoard.boardTiles[bishopQueen.getX()-k][bishopQueen.getY()-k];
                            testIfPieceThreatensKing(bishopQueen,checkedTile);
                            k++;
                        }
                        currentBoard.allPossibleMoves.addAll(bishopQueen.possibleMoves);
                        currentTile.getPiece().possibleMoves.clear();
                    }
                }
            }
            if (this.x - i >0 && this.y - i >0) {
                //If there's a bishop lower-left of king, check it's upper-right moves
                currentTile = currentBoard.boardTiles[this.x - i][this.y - i];
                if (currentTile.hasPiece &&
                        !(currentTile.getPiece().pieceColor.equals(this.pieceColor))) {
                    if (currentTile.getPiece() instanceof Bishop || currentTile.getPiece() instanceof Queen) {
                        Piece bishopQueen = currentTile.getPiece();
                        this.threatPieces.add(bishopQueen);
                        int k=1;
                        while(bishopQueen.getX()+k<8 && bishopQueen.getY()+k<8){
                            Tile checkedTile= currentBoard.boardTiles[bishopQueen.getX()+k][bishopQueen.getY()+k];
                            testIfPieceThreatensKing(bishopQueen,checkedTile);
                            k++;
                        }
                        currentBoard.allPossibleMoves.addAll(bishopQueen.possibleMoves);
                        currentTile.getPiece().possibleMoves.clear();
                    }
                }
            }
            //UP-LEFT LOWER-RIGHT DIAGONAL
            if (this.x - i >0 && this.y + i < 8) {
                //If there's a bishop upper-left of king, check it's lower-right moves
                currentTile = currentBoard.boardTiles[this.x - i][this.y + i];
                if (currentTile.hasPiece &&
                        !(currentTile.getPiece().pieceColor.equals(this.pieceColor))) {
                    if (currentTile.getPiece() instanceof Bishop || currentTile.getPiece() instanceof Queen) {
                        Piece bishopQueen = currentTile.getPiece();
                        this.threatPieces.add(bishopQueen);
                        int k=1;
                        while(bishopQueen.getX()+k<8 && bishopQueen.getY()-k>0){
                            Tile checkedTile= currentBoard.boardTiles[bishopQueen.getX()+k][bishopQueen.getY()-k];
                            testIfPieceThreatensKing(bishopQueen,checkedTile);
                            k++;
                        }
                        currentBoard.allPossibleMoves.addAll(bishopQueen.possibleMoves);
                        currentTile.getPiece().possibleMoves.clear();
                    }
                }
            }
            if (this.x +i < 8 && this.y - i >0) {
                //If there's a bishop lower-left of king, check it's upper-right moves
                currentTile = currentBoard.boardTiles[this.x + i][this.y - i];
                if (currentTile.hasPiece &&
                        !(currentTile.getPiece().pieceColor.equals(this.pieceColor))) {
                    if (currentTile.getPiece() instanceof Bishop || currentTile.getPiece() instanceof Queen) {
                        Piece bishopQueen = currentTile.getPiece();
                        this.threatPieces.add(bishopQueen);
                        int k=1;
                        while(bishopQueen.getX()-k>0 && bishopQueen.getY()+k<8){
                            Tile checkedTile= currentBoard.boardTiles[bishopQueen.getX()-k][bishopQueen.getY()+k];
                            testIfPieceThreatensKing(bishopQueen,checkedTile);
                            k++;
                        }
                        currentBoard.allPossibleMoves.addAll(bishopQueen.possibleMoves);
                        currentTile.getPiece().possibleMoves.clear();
                    }
                }
            }
        }

        System.out.printf("----------------%s KING'S INFO-----------------", this.pieceColor.toString().toUpperCase());
        System.out.println();
        System.out.println("KING'S POSSIBLE BEFORE: ");
        for (Tile move : this.possibleMoves) System.out.println("X" + move.x + " Y" + move.y);

        for (Tile allPossibleMove : currentBoard.allPossibleMoves) {
            if(this.possibleMoves.contains(allPossibleMove)) {
                this.possibleMoves.remove(allPossibleMove);
                this.illegalMoves.add(allPossibleMove);
            }
        }

//        System.out.println(this.possibleMoves);
        System.out.println("KING'S POSSIBLE AFTER: ");
        for (Tile possibleMove : this.possibleMoves) System.out.println("X" + possibleMove.x + " Y" + possibleMove.y);

        System.out.println("---------------------------------------------------");

        currentBoard.allPossibleMoves.clear();
    }

    public boolean testIfPieceThreatensKing(Piece threatPiece, Tile checkedTile) {
        if (checkedTile.getPiece() == null) {
            threatPiece.possibleMoves.add(checkedTile);
        } else if (!checkedTile.getPiece().pieceColor.equals(threatPiece.pieceColor)) {
            if (checkedTile.getPiece() instanceof King)
                threatPiece.possibleMoves.add(checkedTile);
            else {
                threatPiece.possibleMoves.add(checkedTile);
                isInDanger = true;
                this.threatPiece = threatPiece;
                return true;
            }
        }
        //
//        System.out.println("isInDanger: "+ isInDanger);
//        System.out.println("threat piece: "+ threatPiece.pieceType);
//        System.out.print("X"+threatPiece.getX());
//        System.out.println("Y"+threatPiece.getY());
        //
        return false;
    }

    public boolean isInCheck(Board currentBoard) {
        Tile kingTile = currentBoard.boardTiles[this.x][this.y];
        System.out.println("King X"+this.x+" Y"+this.y);
//        System.out.println("----------------ALL PIECES POSSIBLE MOVES---------------");
//        this.findPossibleMoves(currentBoard);
//        this.wouldBeInDanger(currentBoard);

        if(this.pieceColor.equals(PieceColor.White)) {
            currentBoard.findAllPossibleMovesOfOpponent(PieceColor.Black);
        } else {
            currentBoard.findAllPossibleMovesOfOpponent(PieceColor.White);
        }

//        for (int x = 0; x < 8; x++) {
//            for (int y = 0; y < 8; y++) {
//                Tile currentTile = currentBoard.boardTiles[x][y];
//                if (currentTile.hasPiece &&
//                        !(currentTile.getPiece().pieceColor.equals(this.pieceColor))) {
//                    //currentTile.getPiece().findPossibleMoves(currentBoard);
////                    if (currentTile.getPiece() instanceof Pawn pawn) {
////                        pawn.possibleMoves.remove(currentBoard.boardTiles[pawn.getX()][pawn.getY() + 1]);
////                    }
//                    //allPossibleMoves.addAll(currentTile.getPiece().possibleMoves);
//
//
////                    //
////                    System.out.println(currentTile.getPiece().pieceColor + " "
////                            + currentTile.getPiece().pieceType + " X" + currentTile.getPiece().getX() + " Y" +
////                            currentTile.getPiece().getY());
////                    System.out.println(currentTile.getPiece().possibleMoves.size());
////                    for (int i = 0; i < currentTile.getPiece().possibleMoves.size(); i++)
////                        System.out.println("X" + currentTile.getPiece().possibleMoves.get(i).x + " Y" + currentTile.getPiece().possibleMoves.get(i).y);
////                    //
//
//
//                    currentTile.getPiece().possibleMoves.clear();
//                }
//            }
//        }
//        System.out.println("---------------------------------------------------------");

//                    for (Tile allPossibleMove : allPossibleMoves) {
        if (currentBoard.allPossibleMoves.contains(kingTile))
            isInCheck = true;
        else
            isInCheck = false;
//                        else this.possibleMoves.remove(allPossibleMove);
//                    }
        currentBoard.allPossibleMoves.clear();
        //this.possibleMoves.clear();
        return isInCheck;
    }

    public boolean isCheckmated (Board currentBoard){
        this.findPossibleMoves(currentBoard);
        this.wouldBeInDanger(currentBoard);
        if (this.isInCheck && this.possibleMoves.isEmpty())
            return this.isCheckmated=true;
        return this.isCheckmated=false;
    }

    public static void main (String[] args) {
        King testKing= new King(3,0,PieceColor.White);
        Board testboard= new Board();
        testKing.findPossibleMoves(testboard);
        System.out.println(testKing.possibleMoves);
    }

}