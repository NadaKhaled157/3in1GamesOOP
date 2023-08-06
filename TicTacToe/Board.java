package TicTacToe;

public class Board {
    xoTile[][] tiles= new xoTile[3][3];
    MarkType winner;

    Board(){
        for (int x=0;x<3;x++){
            for (int y=0;y<3;y++) {
                this.tiles[x][y]= new xoTile();
            }
        }
    }

//    public boolean isAvailableTile(TicTacToe.Tile tileNumber) {
//        boolean isAvailable = false;
//        if (!tileNumber.hasMark)
//            isAvailable = true;
//        return isAvailable;
//    }

    public boolean isBoardFull() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (!this.tiles[x][y].hasMark)
                    return false;
            }
        }
        return true;
    }//this method is probably useless

    public boolean isGameOver() {
        if ((tiles[0][0].hasMark && tiles[0][1].hasMark && tiles[0][2].hasMark) &&
                (tiles[0][0].markType.equals(tiles[0][1].markType) && tiles[0][0].markType.equals(tiles[0][2].markType))) {
            winner = tiles[0][0].markType;
            return true;
        }
        if ((tiles[1][0].hasMark && tiles[1][1].hasMark && tiles[1][2].hasMark) &&
                (tiles[1][0].markType.equals(tiles[1][1].markType) && tiles[1][0].markType.equals(tiles[1][2].markType))) {
            winner = tiles[1][0].markType;
            return true;
        }
        if ((tiles[2][0].hasMark && tiles[2][1].hasMark && tiles[2][2].hasMark) &&
                (tiles[2][0].markType.equals(tiles[2][1].markType) && tiles[2][0].markType.equals(tiles[2][2].markType))) {
            winner = tiles[2][0].markType;
            return true;
        }


        if ((tiles[0][0].hasMark && tiles[1][0].hasMark && tiles[2][0].hasMark) &&
                (tiles[0][0].markType.equals(tiles[1][0].markType) && tiles[0][0].markType.equals(tiles[2][0].markType))) {
            winner = tiles[0][0].markType;
            return true;
        }
        if ((tiles[0][1].hasMark && tiles[1][1].hasMark && tiles[2][1].hasMark) &&
                (tiles[0][1].markType.equals(tiles[1][1].markType) && tiles[0][1].markType.equals(tiles[2][1].markType))) {
            winner = tiles[0][1].markType;
            return true;
        }
        if ((tiles[0][2].hasMark && tiles[1][2].hasMark && tiles[2][2].hasMark) &&
                (tiles[0][2].markType.equals(tiles[1][2].markType) && tiles[0][2].markType.equals(tiles[2][2].markType))) {
            winner = tiles[0][2].markType;
            return true;
        }


        if ((tiles[0][0].hasMark && tiles[1][1].hasMark && tiles[2][2].hasMark) &&
                (tiles[0][0].markType.equals(tiles[1][1].markType) && tiles[0][0].markType.equals(tiles[2][2].markType))) {
            winner = tiles[0][0].markType;
            return true;
        }
        if ((tiles[0][2].hasMark && tiles[1][1].hasMark && tiles[2][0].hasMark) &&
                (tiles[0][2].markType.equals(tiles[1][1].markType) && tiles[1][1].markType.equals(tiles[2][0].markType))){
            winner = tiles[0][2].markType;
            return true;
        }

        return false;
    }
}



//for (int x=0;x<4;x++){
//        if (tile[x][0].hasMark)
//        }
//        if ((tileOne.hasMark && tileTwo.hasMark && tileThree.hasMark) &&
//        (tileOne.markType.equals(tileTwo.markType) && tileThree.markType.equals(tileOne.markType))) {
//        return tileOne.markType;
//        }
//        if ((tileFour.hasMark && tileFive.hasMark && tileSix.hasMark) &&
//        (tileFour.markType.equals(tileFive.markType) && tileSix.markType.equals(tileFour.markType))) {
//        return tileFour.markType;
//        }
//        if ((tileSeven.hasMark && tileEight.hasMark && tileNine.hasMark) &&
//        (tileSeven.markType.equals(tileEight.markType) && tileNine.markType.equals(tileSeven.markType))) {
//        return tileOne.markType;
//        }
//        if ((tileOne.hasMark && tileFive.hasMark && tileNine.hasMark)
//        && tileFive.markType.equals(tileOne.markType) && tileNine.markType.equals(tileOne.markType)){
//        return tileOne.markType;
//        }
//        if ((tileThree.hasMark&&tileFive.hasMark&&tileSeven.hasMark)
//        &&tileFive.markType.equals(tileThree.markType)&&tileSeven.markType.equals(tileThree.markType)) {
//        return tileOne.markType;
//        }
// add vertical movements and find a smarter way to do this using arrays