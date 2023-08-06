package Chessmate;

import java.util.ArrayList;

public class Pawn extends Piece {

    Pawn(int x, int y, PieceColor pieceColor) {
        this.x = x;
        this.y = y;
        this.pieceType = PieceType.Pawn;
        this.pieceColor = pieceColor;
    }

    public ArrayList<Tile> findPossibleMoves(Board currentBoard) {
        int[]X={this.x+1,this.x-1};
        // White Pawn
        if (this.pieceColor.equals(PieceColor.White)) {
            int[] Y={this.y+1,this.y+1};
            //First Move
            if (this.y == 1) {
                if (!currentBoard.boardTiles[this.x][this.y+2].hasPiece) {
                    this.possibleMoves.add(currentBoard.boardTiles[this.x][this.y + 2]);
                } else
                    this.illegalMoves.add(currentBoard.boardTiles[this.x][this.y + 2]);
            }
            //Regular Move
            if (y!=7) {
                if (!currentBoard.boardTiles[this.x][this.y + 1].hasPiece)
                    this.possibleMoves.add(currentBoard.boardTiles[this.x][this.y + 1]);
                else this.illegalMoves.add(currentBoard.boardTiles[this.x][this.y + 1]);
            }
            //Diagonal Attack
            for (int i=0;i<X.length;i++) {
                if (X[i] < 0 || X[i] > 7 || Y[i] < 0 || Y[i] > 7)
                    continue;
                if (currentBoard.boardTiles[X[i]][Y[i]].hasPiece) {
                    if (currentBoard.boardTiles[X[i]][Y[i]].getPiece().pieceColor.equals(PieceColor.Black))
                        this.possibleMoves.add(currentBoard.boardTiles[X[i]][Y[i]]);
                    else this.illegalMoves.add(currentBoard.boardTiles[X[i]][Y[i]]);
                }
            }
//            if (x == 0) {
//                if (currentBoard.boardTiles[this.x+1][this.y+1].hasPiece){
//                    if (currentBoard.boardTiles[this.x+1][this.y+1].getPiece().pieceColor.equals(PieceColor.Black))
//                        this.possibleMoves.add(currentBoard.boardTiles[this.x + 1][this.y + 1]);
//                    else this.illegalMoves.add(currentBoard.boardTiles[this.x + 1][this.y + 1]);
//                }
//            } else if (x==7) {
//                if (currentBoard.boardTiles[this.x-1][this.y+1].hasPiece){
//                    if (currentBoard.boardTiles[this.x-1][this.y+1].getPiece().pieceColor.equals(PieceColor.Black))
//                        this.possibleMoves.add(currentBoard.boardTiles[this.x - 1][this.y + 1]);
//                    else this.illegalMoves.add(currentBoard.boardTiles[this.x - 1][this.y + 1]);
//                }
//            } else {
//                if (currentBoard.boardTiles[this.x+1][this.y+1].hasPiece){
//                    if (currentBoard.boardTiles[this.x+1][this.y+1].getPiece().pieceColor.equals(PieceColor.Black))
//                        this.possibleMoves.add(currentBoard.boardTiles[this.x+1][this.y+1]);
//                    else this.illegalMoves.add(currentBoard.boardTiles[this.x+1][this.y+1]);
//                }
//                if (currentBoard.boardTiles[this.x-1][this.y+1].hasPiece){
//                    if (currentBoard.boardTiles[this.x-1][this.y+1].getPiece().pieceColor.equals(PieceColor.Black))
//                        this.possibleMoves.add(currentBoard.boardTiles[this.x-1][this.y+1]);
//                    else this.illegalMoves.add(currentBoard.boardTiles[this.x-1][this.y+1]);
//                }
//            }
        }

        // Black Pawn
        if (this.pieceColor.equals(PieceColor.Black)) {
            int[] Y={this.y-1,this.y-1};
//            if (!currentBoard.boardTiles[x][y+1].hasPiece) {
            //First Move
            if (this.y == 6) {
                if (!currentBoard.boardTiles[x][y-2].hasPiece) {
                    this.possibleMoves.add(currentBoard.boardTiles[this.x][this.y-2]);
                } else
                    this.illegalMoves.add(currentBoard.boardTiles[this.x][this.y-2]);
            }
            //Regular Move
            if (y!=0) {
                if (!currentBoard.boardTiles[this.x][this.y - 1].hasPiece)
                    this.possibleMoves.add(currentBoard.boardTiles[this.x][this.y - 1]);
                else this.illegalMoves.add(currentBoard.boardTiles[this.x][this.y - 1]);
            }
            //Diagonal Attack
            for (int i=0;i<X.length;i++) {
                if (X[i] < 0 || X[i] > 7 || Y[i] < 0 || Y[i] > 7)
                    continue;
                if (currentBoard.boardTiles[X[i]][Y[i]].hasPiece) {
                    if (currentBoard.boardTiles[X[i]][Y[i]].getPiece().pieceColor.equals(PieceColor.White))
                        this.possibleMoves.add(currentBoard.boardTiles[X[i]][Y[i]]);
                    else this.illegalMoves.add(currentBoard.boardTiles[X[i]][Y[i]]);
                }
            }
//            if (x==0) {
//                if (currentBoard.boardTiles[this.x+1][this.y-1].hasPiece){
//                    if (currentBoard.boardTiles[this.x+1][this.y-1].getPiece().pieceColor.equals(PieceColor.White))
//                        this.possibleMoves.add(currentBoard.boardTiles[this.x+1][this.y-1]);
//                    else this.illegalMoves.add(currentBoard.boardTiles[this.x+1][this.y-1]);
//                }
//            } else if (x==7) {
//                if (currentBoard.boardTiles[this.x-1][this.y-1].hasPiece){
//                    if (currentBoard.boardTiles[this.x-1][this.y-1].getPiece().pieceColor.equals(PieceColor.White))
//                        this.possibleMoves.add(currentBoard.boardTiles[this.x-1][this.y-1]);
//                    else this.illegalMoves.add(currentBoard.boardTiles[this.x-1][this.y-1]);
//                }
//            } else {
//                if (currentBoard.boardTiles[this.x+1][this.y-1].hasPiece){
//                    if (currentBoard.boardTiles[this.x+1][this.y-1].getPiece().pieceColor.equals(PieceColor.White))
//                        this.possibleMoves.add(currentBoard.boardTiles[this.x+1][this.y-1]);
//                    else this.illegalMoves.add(currentBoard.boardTiles[this.x+1][this.y-1]);
//                }
//                if (currentBoard.boardTiles[this.x-1][this.y-1].hasPiece){
//                    if (currentBoard.boardTiles[this.x-1][this.y-1].getPiece().pieceColor.equals(PieceColor.White))
//                        this.possibleMoves.add(currentBoard.boardTiles[this.x-1][this.y-1]);
//                    else this.illegalMoves.add(currentBoard.boardTiles[this.x-1][this.y-1]);
//                }
//            }
        }
//        if (this.pieceColor.toString().equals("Black")) {
//            if (!currentBoard.boardTiles[x][y - 1].hasPiece) {
//                //First Move
//                if (this.y == 6 && !currentBoard.boardTiles[x][y - 2].hasPiece) {
//                    this.possibleMoves.add(currentBoard.boardTiles[this.x][this.y - 2]);
//                }
//                //Regular Move
//                this.possibleMoves.add(currentBoard.boardTiles[this.x][this.y - 1]);
//                //Diagonal Attack
//                if (x==0){
//                    if (currentBoard.boardTiles[x + 1][y - 1].hasPiece && currentBoard.boardTiles[x + 1][y - 1].getPiece().pieceColor.equals(PieceColor.White))
//                        this.possibleMoves.add(currentBoard.boardTiles[this.x + 1][this.y - 1]);
//                } else if (x==7){
//                    if (currentBoard.boardTiles[x - 1][y - 1].hasPiece && currentBoard.boardTiles[x - 1][y - 1].getPiece().pieceColor.equals(PieceColor.White))
//                        this.possibleMoves.add(currentBoard.boardTiles[this.x - 1][this.y - 1]);
//                } else {
//                    if (currentBoard.boardTiles[x + 1][y - 1].hasPiece && currentBoard.boardTiles[x + 1][y - 1].getPiece().pieceColor.equals(PieceColor.White))
//                        this.possibleMoves.add(currentBoard.boardTiles[this.x + 1][this.y - 1]);
//                    if (currentBoard.boardTiles[x - 1][y - 1].hasPiece && currentBoard.boardTiles[x - 1][y - 1].getPiece().pieceColor.equals(PieceColor.White))
//                        this.possibleMoves.add(currentBoard.boardTiles[this.x - 1][this.y - 1]);
//                }
//            }
//        }
        return this.possibleMoves;
    }

    public boolean canPromote(Board currentBoard){
        //White Pawn promotion
        if(currentBoard.boardTiles[x][y].getPiece().pieceColor.equals(PieceColor.White) && this.y==7){
            return true;
        }

        //Black Pawn Promotion
        if (currentBoard.boardTiles[x][y].getPiece().pieceColor.equals(PieceColor.Black) && this.y==0){
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
