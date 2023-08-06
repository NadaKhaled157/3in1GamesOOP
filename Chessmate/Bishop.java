package Chessmate;

import java.util.ArrayList;

public class Bishop extends Piece {

    Bishop(int x, int y, PieceColor pieceColor) {
        this.x = x;
        this.y = y;
        this.pieceType = PieceType.Bishop;
        this.pieceColor = pieceColor;
    }

    @Override
    public ArrayList<Tile> findPossibleMoves(Board currentBoard) {
        //If Bishop is not at the top
//        if (this.y != 7) {
        //Up-Right Movement
        for (int i = 1; ((this.x + i) < 8 && (this.y + i) < 8); i++) {
            if (currentBoard.boardTiles[this.x + i][this.y + i].hasPiece) {
                if (!currentBoard.boardTiles[this.x + i][this.y + i].getPiece().pieceColor.equals(this.pieceColor)) {
                    this.possibleMoves.add(currentBoard.boardTiles[this.x + i][this.y + i]);
                    //Bishop is blocked by other player's piece
                        break;
                } else {
                    this.illegalMoves.add(currentBoard.boardTiles[this.x + i][this.y + i]);
                    //Bishop is blocked by the same player's piece
                    break;
                }
            } else this.possibleMoves.add(currentBoard.boardTiles[this.x + i][this.y + i]);
        }
        //Up-Left Movement
        for (int i = 1; ((this.x - i) >= 0 && (this.y + i) < 8); i++) {
            if (currentBoard.boardTiles[this.x - i][this.y + i].hasPiece) {
                if (!currentBoard.boardTiles[this.x - i][this.y + i].getPiece().pieceColor.equals(this.pieceColor)) {
                    this.possibleMoves.add(currentBoard.boardTiles[this.x - i][this.y + i]);
                    break;
                } else {
                    this.illegalMoves.add(currentBoard.boardTiles[this.x - i][this.y + i]);
                    break;
                }
            } else this.possibleMoves.add(currentBoard.boardTiles[this.x - i][this.y + i]);
        }
//        }
        //If Bishop is not at the bottom
//        if (this.y != 0) {
        //Down-Right Movement
        for (int i = 1; ((this.x + i) < 8 && (this.y - i) >= 0); i++) {
            if (currentBoard.boardTiles[this.x + i][this.y - i].hasPiece) {
                if (!currentBoard.boardTiles[this.x + i][this.y - i].getPiece().pieceColor.equals(this.pieceColor)) {
                    this.possibleMoves.add(currentBoard.boardTiles[this.x + i][this.y - i]);
                    break;
                } else {
                    this.illegalMoves.add(currentBoard.boardTiles[this.x + i][this.y - i]);
                    break;
                }
            } else this.possibleMoves.add(currentBoard.boardTiles[this.x + i][this.y - i]);
        }
        //Down-Left Movement
        for (int i = 1; ((this.x - i) >= 0 && (this.y - i) >= 0); i++) {
            if (currentBoard.boardTiles[this.x - i][this.y - i].hasPiece) {
                if (!currentBoard.boardTiles[this.x - i][this.y - i].getPiece().pieceColor.equals(this.pieceColor)) {
                    this.possibleMoves.add(currentBoard.boardTiles[this.x - i][this.y - i]);
                    break;
                } else {
                    this.illegalMoves.add(currentBoard.boardTiles[this.x - i][this.y - i]);
                    break;
                }
            } else this.possibleMoves.add(currentBoard.boardTiles[this.x - i][this.y - i]);
        }
//        }
        return this.possibleMoves;
    }

//    @Override
//    public ArrayList<Tile> findIllegalMoves(Board currentBoard, int x, int y) {
//        this.illegalMoves.add(currentBoard.boardTiles[x][y]);
//        return this.illegalMoves;
//    }
}
