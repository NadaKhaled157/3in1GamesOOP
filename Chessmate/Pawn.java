package Chessmate;

import java.util.ArrayList;

public class Pawn extends Piece {

    boolean isFirstMove; //used in en passant
    ArrayList<Piece> threatenedPawn=new ArrayList<>(); //threatened by enPassant
    ArrayList<Tile> enPassantTile= new ArrayList<>(); //tile available as possible move when en passant is allowed

    Pawn(int x, int y, PieceColor pieceColor) {
        this.x = x;
        this.y = y;
        this.pieceType = PieceType.Pawn;
        this.pieceColor = pieceColor;
    }

    public ArrayList<Tile> findPossibleMoves(Board currentBoard) {
        int[] X = {this.x + 1, this.x - 1};
        // White Pawn
        if (this.pieceColor.equals(PieceColor.White)) {
            int[] Y = {this.y + 1, this.y + 1};
            //First Move
            if (this.y == 1) {
                if (!currentBoard.boardTiles[this.x][this.y + 2].hasPiece) {
                    if (!currentBoard.boardTiles[this.x][this.y + 1].hasPiece)
                        this.possibleMoves.add(currentBoard.boardTiles[this.x][this.y + 2]);
                } else
                    this.illegalMoves.add(currentBoard.boardTiles[this.x][this.y + 2]);
            }
            //Regular Move
            if (y != 7) {
                if (!currentBoard.boardTiles[this.x][this.y + 1].hasPiece)
                    this.possibleMoves.add(currentBoard.boardTiles[this.x][this.y + 1]);
                else this.illegalMoves.add(currentBoard.boardTiles[this.x][this.y + 1]);
            }
            //Diagonal Attack
            for (int i = 0; i < X.length; i++) {
                if (X[i] < 0 || X[i] > 7 || Y[i] < 0 || Y[i] > 7)
                    continue;
                if (currentBoard.boardTiles[X[i]][Y[i]].hasPiece) {
                    if (currentBoard.boardTiles[X[i]][Y[i]].getPiece().pieceColor.equals(PieceColor.Black))
                        this.possibleMoves.add(currentBoard.boardTiles[X[i]][Y[i]]);
                    else this.illegalMoves.add(currentBoard.boardTiles[X[i]][Y[i]]);
                }
            }

        }

        // Black Pawn
        if (this.pieceColor.equals(PieceColor.Black)) {
            int[] Y = {this.y - 1, this.y - 1};
            //First Move
            if (this.y == 6) {
                if (!currentBoard.boardTiles[x][y - 2].hasPiece) {
                    if (!currentBoard.boardTiles[this.x][this.y - 1].hasPiece)
                        this.possibleMoves.add(currentBoard.boardTiles[this.x][this.y - 2]);
                } else
                    this.illegalMoves.add(currentBoard.boardTiles[this.x][this.y - 2]);
            }
            //Regular Move
            if (y != 0) {
                if (!currentBoard.boardTiles[this.x][this.y - 1].hasPiece)
                    this.possibleMoves.add(currentBoard.boardTiles[this.x][this.y - 1]);
                else this.illegalMoves.add(currentBoard.boardTiles[this.x][this.y - 1]);
            }
            //Diagonal Attack
            for (int i = 0; i < X.length; i++) {
                if (X[i] < 0 || X[i] > 7 || Y[i] < 0 || Y[i] > 7)
                    continue;
                if (currentBoard.boardTiles[X[i]][Y[i]].hasPiece) {
                    if (currentBoard.boardTiles[X[i]][Y[i]].getPiece().pieceColor.equals(PieceColor.White))
                        this.possibleMoves.add(currentBoard.boardTiles[X[i]][Y[i]]);
                    else this.illegalMoves.add(currentBoard.boardTiles[X[i]][Y[i]]); //maybe remove?
                }
            }
        }
        //this.canPerformEnPassant(currentBoard);
        return this.possibleMoves;
    }

    public boolean canPerformEnPassant(Board currentBoard) {
        //White Pawns
            if (this.pieceColor.equals(PieceColor.White) && this.y == 4) {
                if (this.x != 0) {
                    if (currentBoard.boardTiles[this.x - 1][this.y].getPiece() != null)
                        if (currentBoard.boardTiles[this.x - 1][this.y].getPiece() instanceof Pawn &&
                                currentBoard.boardTiles[this.x - 1][this.y].getPiece().pieceColor.equals(PieceColor.Black)) {
                            threatenedPawn.add(currentBoard.boardTiles[this.x - 1][this.y].getPiece());
                            enPassantTile.add(currentBoard.boardTiles[this.x - 1][this.y+1]);
                        return true;
                        }
                }
                if (this.x != 7) {
                    if (currentBoard.boardTiles[this.x + 1][this.y].getPiece() != null)
                        if (currentBoard.boardTiles[this.x + 1][this.y].getPiece() instanceof Pawn &&
                                currentBoard.boardTiles[this.x + 1][this.y].getPiece().pieceColor.equals(PieceColor.Black)) {
                            threatenedPawn.add(currentBoard.boardTiles[this.x + 1][this.y].getPiece());
                            enPassantTile.add(currentBoard.boardTiles[this.x + 1][this.y+1]);
                            return true;
                        }
                }
                System.out.println("ENPASSANT SIZE: "+enPassantTile.size());
                System.out.println("THREAT SIZE: "+ threatenedPawn.size());
            }
        //Black Pawns
        if (this.pieceColor.equals(PieceColor.Black) && this.y == 3) {
            if(this.x !=0) {
                if (currentBoard.boardTiles[this.x - 1][this.y].getPiece() != null)
                    if (currentBoard.boardTiles[this.x - 1][this.y].getPiece() instanceof Pawn &&
                            currentBoard.boardTiles[this.x - 1][this.y].getPiece().pieceColor.equals(PieceColor.White)) {
                        threatenedPawn.add(currentBoard.boardTiles[this.x - 1][this.y].getPiece());
                        enPassantTile.add(currentBoard.boardTiles[this.x - 1][this.y-1]);
                        return true;
                    }
            }
            if (this.x !=7) {
                if (currentBoard.boardTiles[this.x + 1][this.y].getPiece() != null)
                    if (currentBoard.boardTiles[this.x + 1][this.y].getPiece() instanceof Pawn &&
                            currentBoard.boardTiles[this.x + 1][this.y].getPiece().pieceColor.equals(PieceColor.White)) {
                        threatenedPawn.add(currentBoard.boardTiles[this.x + 1][this.y].getPiece());
                        enPassantTile.add(currentBoard.boardTiles[this.x + 1][this.y-1]);
                        return true;
                    }
            }
        }
    return false;}
    public boolean canPromote(Board currentBoard){
        //White Pawn promotion
        if(currentBoard.boardTiles[this.x][this.y].getPiece().pieceColor.equals(PieceColor.White) && this.y==7){
            return true;
        }

        //Black Pawn Promotion
        if (currentBoard.boardTiles[this.x][this.y].getPiece().pieceColor.equals(PieceColor.Black) && this.y==0){
            return true;
        }
        return false;}

    public void wouldEndangerKing(Board currentBoard){
        //add diagonal moves to pawns because if the king moves diagonal to it
        //the pawn can now eat it, when it previously couldn't move diagonally
        //which would put king in checkmate, so not allowed
        int[]X={this.x+1,this.x-1};
        if (this.pieceColor.equals(PieceColor.White)){
            int[]Y={this.y+1,this.y+1};
            for (int i=0;i<X.length;i++) {
                if (X[i] < 0 || X[i] > 7 || Y[i] < 0 || Y[i] > 7)
                    continue;
                this.possibleMoves.add(currentBoard.boardTiles[X[i]][Y[i]]);
            }
        } else {
            int[]Y={this.y-1,this.y-1};
            for (int i=0;i<X.length;i++) {
                if (X[i] < 0 || X[i] > 7 || Y[i] < 0 || Y[i] > 7)
                    continue;
                this.possibleMoves.add(currentBoard.boardTiles[X[i]][Y[i]]);

            }
        }
    }
}
