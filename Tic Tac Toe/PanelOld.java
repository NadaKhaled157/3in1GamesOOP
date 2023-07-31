package TicTacToe;

import TicTacToe.MarkType;

public class PanelOld {
    Tile tileOne;
    Tile tileTwo;
    Tile tileThree;
    Tile tileFour;
    Tile tileFive;
    Tile tileSix;
    Tile tileSeven;
    Tile tileEight;
    Tile tileNine;

    public boolean isPossibleTile(Tile tileNumber) {
        boolean isPossible = false;
        if (!tileNumber.hasMark)
            isPossible = true;
        return isPossible;
    }

    public boolean isPanelFull(Tile tileOne, Tile tileTwo, Tile tileThree, Tile tileFour,
                               Tile tileFive, Tile tileSix, Tile tileSeven, Tile tileEight, Tile tileNine){
        if (tileOne.hasMark&&tileTwo.hasMark&&tileThree.hasMark&&tileFour.hasMark&&tileFive.hasMark&&
                tileSix.hasMark&&tileSeven.hasMark&&tileEight.hasMark&&tileNine.hasMark) {
            return true;
        }
        return false; } //this method is probably useless

    public MarkType isGameOver() {
        if ((tileOne.hasMark && tileTwo.hasMark && tileThree.hasMark) &&
                (tileOne.markType.equals(tileTwo.markType) && tileThree.markType.equals(tileOne.markType))) {
            return tileOne.markType;
        }
        if ((tileFour.hasMark && tileFive.hasMark && tileSix.hasMark) &&
                (tileFour.markType.equals(tileFive.markType) && tileSix.markType.equals(tileFour.markType))) {
            return tileFour.markType;
        }
        if ((tileSeven.hasMark && tileEight.hasMark && tileNine.hasMark) &&
                (tileSeven.markType.equals(tileEight.markType) && tileNine.markType.equals(tileSeven.markType))) {
            return tileOne.markType;
        }
        if ((tileOne.hasMark && tileFive.hasMark && tileNine.hasMark)
                && tileFive.markType.equals(tileOne.markType) && tileNine.markType.equals(tileOne.markType)){
            return tileOne.markType;
        }
        if ((tileThree.hasMark&&tileFive.hasMark&&tileSeven.hasMark)
                &&tileFive.markType.equals(tileThree.markType)&&tileSeven.markType.equals(tileThree.markType)) {
            return tileOne.markType;
        }
        // add vertical movements and find a smarter way to do this using arrays
        return null;
    }
}
