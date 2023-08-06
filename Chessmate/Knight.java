package Chessmate;

import java.util.ArrayList;

public class Knight extends Piece{
    Knight(int x, int y, PieceColor pieceColor) {
        this.x = x;
        this.y = y;
        this.pieceType = PieceType.Knight;
        this.pieceColor = pieceColor;
    }
    @Override
    public ArrayList<Tile> findPossibleMoves(Board currentBoard) {
        int[] X ={this.x+1,this.x+2,this.x+2,this.x+1,this.x-1,this.x-2,this.x-2,this.x-1};
        int[] Y ={this.y+2,this.y+1,this.y-1,this.y-2,this.y-2,this.y-1,this.y+1,this.y+2};

        for(int i=0;i<X.length;i++){
            if (X[i] < 0 || X[i] > 7 || Y[i] < 0 || Y[i] > 7)
                continue;
            Tile tile = currentBoard.boardTiles[X[i]][Y[i]];
            if (tile.hasPiece){
                if(!tile.getPiece().pieceColor.equals(this.pieceColor))
                    this.possibleMoves.add(tile);
                else this.illegalMoves.add(tile);
            } else
                this.possibleMoves.add(tile);
        }

        return this.possibleMoves;
    }
}
