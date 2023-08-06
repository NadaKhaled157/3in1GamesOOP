package Chessmate;

import java.util.ArrayList;

public class Rook extends Piece {

    Rook(int x, int y, PieceColor pieceColor) {
        this.x = x;
        this.y = y;
        this.pieceType = PieceType.Rook;
        this.pieceColor = pieceColor;
    }

    @Override
    public ArrayList<Tile> findPossibleMoves(Board currentBoard) {
        // Up Movement
        for (int i = 1; (this.y+i < 8); i++) {
            if (currentBoard.boardTiles[this.x][this.y + i].hasPiece) {
                if (!currentBoard.boardTiles[this.x][this.y + i].getPiece().pieceColor.equals(this.pieceColor)) {
                    this.possibleMoves.add(currentBoard.boardTiles[this.x][this.y + i]);
                    //Rook is blocked by other player's piece
                } else {
                    this.illegalMoves.add(currentBoard.boardTiles[this.x][this.y + i]);
                    //Rook is blocked by the same player's piece
                }
                break;
            } else this.possibleMoves.add(currentBoard.boardTiles[this.x][this.y + i]);
        }
        //Down Movement
        for (int i = 1; (this.y-i >=0 ); i++) {
            if (currentBoard.boardTiles[this.x][this.y - i].hasPiece) {
                if (!currentBoard.boardTiles[this.x][this.y - i].getPiece().pieceColor.equals(this.pieceColor)) {
                    //Rook is blocked by other player's piece
                    this.possibleMoves.add(currentBoard.boardTiles[this.x][this.y - i]);
                } else {
                    //Rook is blocked by the same player's piece
                    this.illegalMoves.add(currentBoard.boardTiles[this.x][this.y - i]);
                }
                break;
            } else this.possibleMoves.add(currentBoard.boardTiles[this.x][this.y - i]);
        }
        //Right Movement
        for (int i = 1; (this.x+i <8 ); i++) {
            if (currentBoard.boardTiles[this.x+i][this.y].hasPiece) {
                if (!currentBoard.boardTiles[this.x+i][this.y].getPiece().pieceColor.equals(this.pieceColor)) {
                    //Rook is blocked by other player's piece
                    this.possibleMoves.add(currentBoard.boardTiles[this.x+i][this.y]);
                } else {
                    //Rook is blocked by the same player's piece
                    this.illegalMoves.add(currentBoard.boardTiles[this.x+i][this.y]);
                }
                break;
            } else this.possibleMoves.add(currentBoard.boardTiles[this.x+i][this.y]);
        }
        //Left Movement
        for (int i = 1; (this.x-i >=0 ); i++) {
            if (currentBoard.boardTiles[this.x-i][this.y].hasPiece) {
                if (!currentBoard.boardTiles[this.x-i][this.y].getPiece().pieceColor.equals(this.pieceColor)) {
                    //Rook is blocked by other player's piece
                    this.possibleMoves.add(currentBoard.boardTiles[this.x-i][this.y]);
                } else {
                    //Rook is blocked by the same player's piece
                    this.illegalMoves.add(currentBoard.boardTiles[this.x-i][this.y]);
                }
                break;
            } else this.possibleMoves.add(currentBoard.boardTiles[this.x-i][this.y]);
        }
        return this.possibleMoves;
    }


}
