package Chessmate;

import java.util.ArrayList;

public class King extends Piece{

    ArrayList<Tile> allPossibleMoves= new ArrayList<>(); //possible moves of all other pieces on board
    boolean isInCheck;
    boolean isCheckmated;

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

    public void wouldBeInDanger(Board currentBoard){
        //This method doesn't allow the king to move in
        //a position where it would be in check and therefor checkmate-d
        for (int x=0;x<8;x++) {
            for (int y = 0; y < 8; y++) {
                Tile currentTile=currentBoard.boardTiles[x][y];
                if (currentTile.hasPiece &&
                        !(currentTile.getPiece().pieceColor.equals(this.pieceColor))){
                    currentTile.getPiece().findPossibleMoves(currentBoard);
                    if (currentTile.getPiece() instanceof Pawn pawn) {
                        //remove in front of pawn from possible threatening moves
                        //because pawn cannot eat in front of it
                        pawn.possibleMoves.remove(currentBoard.boardTiles[pawn.getX()][pawn.getY() + 1]);
                        pawn.wouldEndangerKing(currentBoard);
                    } else if (currentTile.getPiece() instanceof Rook || currentTile.getPiece() instanceof Queen){
                        Piece rookQueen= currentTile.getPiece();
                        //UP
                        for(int j= 1; rookQueen.getY()+j<8;j++){
                            Tile checkedTile= currentBoard.boardTiles[rookQueen.getX()][rookQueen.getY()+j];
                            if (checkedTile.getPiece()==null){
                                rookQueen.possibleMoves.add(checkedTile);
                            } else if(!checkedTile.getPiece().pieceColor.equals(rookQueen.pieceColor)) {
                                if (checkedTile.getPiece() instanceof King)
                                    rookQueen.possibleMoves.add(checkedTile);
                                else {
                                    rookQueen.possibleMoves.add(checkedTile);
                                    break;
                                }
                            }
                        }
                    }
                    allPossibleMoves.addAll(currentTile.getPiece().possibleMoves);

                    //
                    System.out.println(currentBoard.boardTiles[x][y].getPiece().pieceColor+" "
                            +currentBoard.boardTiles[x][y].getPiece().pieceType+" X"+currentBoard.boardTiles[x][y].getPiece().getX()+" Y"+
                            currentBoard.boardTiles[x][y].getPiece().getY());
                    System.out.println(currentBoard.boardTiles[x][y].getPiece().possibleMoves.size());
                    for (int i=0;i<currentBoard.boardTiles[x][y].getPiece().possibleMoves.size();i++)
                        System.out.println("X"+currentBoard.boardTiles[x][y].getPiece().possibleMoves.get(i).x+" Y"+currentBoard.boardTiles[x][y].getPiece().possibleMoves.get(i).y);
                    //


                    for (Tile pieceIllegalMoves : currentTile.getPiece().illegalMoves) {
                        //If an opponent's piece(1) can be eaten by the king
                        //But that would allow another opponent's piece to move on the same tile
                        //when it previously couldn't because piece(1) was on the tile
                        //This would put the king in checkmate, so it's not allowed.
                        if (this.possibleMoves.contains(pieceIllegalMoves)) {
                            this.possibleMoves.remove(pieceIllegalMoves);
                            this.illegalMoves.add(pieceIllegalMoves);
                        }
                    }
                    currentTile.getPiece().possibleMoves.clear();
                }
            }
        }
//        System.out.println("ALL: ");
//        for (int i=0;i<allPossibleMoves.size();i++)
//            System.out.println("X"+allPossibleMoves.get(i).x+" Y"+allPossibleMoves.get(i).y);
        System.out.println("KING'S POSSIBLE BEFORE: ");
        for (int i=0;i<this.possibleMoves.size();i++)
            System.out.println("X"+this.possibleMoves.get(i).x+" Y"+this.possibleMoves.get(i).y);

        for (Tile allPossibleMove : allPossibleMoves) {
            if(this.possibleMoves.contains(allPossibleMove)) {
                this.possibleMoves.remove(allPossibleMove);
                this.illegalMoves.add(allPossibleMove);
            }
        }

//        System.out.println(this.possibleMoves);
        System.out.println("KING'S POSSIBLE AFTER: ");
        for (int i=0;i<this.possibleMoves.size();i++)
            System.out.println("X"+this.possibleMoves.get(i).x+" Y"+this.possibleMoves.get(i).y);

        allPossibleMoves.clear();
    }

    public boolean isInCheck(Board currentBoard) {
        Tile kingTile = currentBoard.boardTiles[this.x][this.y];
        System.out.println("King X"+this.x+" Y"+this.y);
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Tile currentTile = currentBoard.boardTiles[x][y];
                if (currentTile.hasPiece &&
                        !(currentTile.getPiece().pieceColor.equals(this.pieceColor))) {
                    currentTile.getPiece().findPossibleMoves(currentBoard);
                    if (currentTile.getPiece() instanceof Pawn pawn) {
                        pawn.possibleMoves.remove(currentBoard.boardTiles[pawn.getX()][pawn.getY() + 1]);
                    }
                    allPossibleMoves.addAll(currentTile.getPiece().possibleMoves);

                    //
                    System.out.println(currentTile.getPiece().pieceColor + " "
                            + currentTile.getPiece().pieceType + " X" + currentTile.getPiece().getX() + " Y" +
                            currentTile.getPiece().getY());
                    System.out.println(currentTile.getPiece().possibleMoves.size());
                    for (int i = 0; i < currentTile.getPiece().possibleMoves.size(); i++)
                        System.out.println("X" + currentTile.getPiece().possibleMoves.get(i).x + " Y" + currentTile.getPiece().possibleMoves.get(i).y);
                    //

                    currentTile.getPiece().possibleMoves.clear();
                }
            }
        }

//                    for (Tile allPossibleMove : allPossibleMoves) {
        if (allPossibleMoves.contains(kingTile))
            isInCheck = true;
        else
            isInCheck = false;
//                        else this.possibleMoves.remove(allPossibleMove);
//                    }
        allPossibleMoves.clear();
        this.possibleMoves.clear();
        return isInCheck;
    }

    public boolean isCheckmated (Board currentBoard){
        this.findPossibleMoves(currentBoard);
        this.wouldBeInDanger(currentBoard);
        if (this.possibleMoves.isEmpty())
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