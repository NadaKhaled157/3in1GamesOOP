package Chessmate;

public class Board {
    Tile[][] boardTiles= new Tile[8][8];
    //Creating reference variables that point to this chessboard's kings
    //So we can determine if they are in check after every move
    //Without having to identify their location each time using a for loop on all board tiles
    King whiteKing;
    King blackKing;

    Board(){
//        System.out.println("CURRENT STATE:");
//        System.out.println("|BR|BK|BB|BQ|BK̂|BB|BK|BR|");
//        System.out.println("|BP|BP|BP|BP|BP|BP|BP|BP|");
//        System.out.println("|__|__|__|__|__|__|__|__|");
//        System.out.println("|__|__|__|__|__|__|__|__|");
//        System.out.println("|__|__|__|__|__|__|__|__|");
//        System.out.println("|__|__|__|__|__|__|__|__|");
//        System.out.println("|WP|WP|WP|WP|WP|WP|WP|WP|");
//        System.out.println("|WR|WK|WB|WQ|WK̂|WB|WK|WR|");
//        System.out.println();
//        System.out.println("TILES COORDINATES:");
//        System.out.println("|0,7|1,7|2,7|3,7|4,7|5,7|6,7|7,7|");
//        System.out.println("|0,6|1,6|2,6|3,6|4,6|5,6|6,6|7,6|");
//        System.out.println("|0,5|1,5|2,5|3,5|4,5|5,5|6,5|7,5|");
//        System.out.println("|0,4|1,4|2,4|3,4|4,4|5,4|6,4|7,4|");
//        System.out.println("|0,3|1,3|2,3|3,3|4,3|5,3|6,3|7,3|");
//        System.out.println("|0,2|1,2|2,2|3,2|4,2|5,2|6,2|7,2|");
//        System.out.println("|0,1|1,1|2,1|3,1|4,1|5,1|6,1|7,1|");
//        System.out.println("|0,0|1,0|2,0|3,0|4,0|5,0|6,0|7,0|");

        // Creating Pieces
//        for (int x=0;x<8;x++){
//            Pawn W
//        }
//        Pawn WP1= new Pawn(0,1);
//        Pawn WP2= new Pawn(1,1);
//        Pawn WP3= new Pawn(2,1);
//        Pawn WP4= new Pawn(3,1);
//        Pawn WP5= new Pawn(4,1);
//        Pawn WP6= new Pawn(5,1);
//        Pawn WP7= new Pawn(6,1);
//        Pawn WP8= new Pawn(7,1);
//
//        Pawn BP1= new Pawn(0,6);
//        Pawn BP2= new Pawn(1,6);
//        Pawn BP3= new Pawn(2,6);
//        Pawn BP4= new Pawn(3,6);
//        Pawn BP5= new Pawn(4,6);
//        Pawn BP6= new Pawn(5,6);
//        Pawn BP7= new Pawn(6,6);
//        Pawn BP8= new Pawn(7,6);

        for (int x=0;x<8;x++) {
            for (int y = 0;y<8;y++) {
                boardTiles[x][y]= new Tile(x,y);
            }
        }
        for (int x=0;x<8;x++){
            for (int y=0;y<2;y++) {
                boardTiles[x][y].hasPiece=true;
            }
            for (int y=7;y>5;y--) {
                boardTiles[x][y].hasPiece=true;
            }
        }
        initializeBoard();
        //testBoard();
        //testKingBoard();
    }

    public void initializeBoard(){
        whiteKing=new King(4,0,PieceColor.White);
        blackKing=new King(4,7,PieceColor.Black);

        //Setting up white pieces
        for (int x = 0; x < 8; x++) {
            this.boardTiles[x][1].setPiece(new Pawn(x,1, PieceColor.White));
            this.boardTiles[x][1].hasPiece=true;
        }
        this.boardTiles[0][0].setPiece(new Rook(0,0,PieceColor.White));
        this.boardTiles[7][0].setPiece(new Rook(7,0,PieceColor.White));
        this.boardTiles[1][0].setPiece(new Knight(1,0,PieceColor.White));
        this.boardTiles[6][0].setPiece(new Knight(6,0,PieceColor.White));
        this.boardTiles[2][0].setPiece(new Bishop(2,0,PieceColor.White));
        this.boardTiles[5][0].setPiece(new Bishop(5,0,PieceColor.White));
        this.boardTiles[3][0].setPiece(new Queen(3,0,PieceColor.White));
        this.boardTiles[4][0].setPiece(whiteKing);

        this.boardTiles[0][0].hasPiece=true;
        this.boardTiles[7][0].hasPiece=true;
        this.boardTiles[1][0].hasPiece=true;
        this.boardTiles[6][0].hasPiece=true;
        this.boardTiles[2][0].hasPiece=true;
        this.boardTiles[5][0].hasPiece=true;
        this.boardTiles[3][0].hasPiece=true;
        this.boardTiles[4][0].hasPiece=true;

        //Setting up black pieces
        for (int x = 0; x < 8; x++) {
            this.boardTiles[x][6].setPiece(new Pawn(x,6, PieceColor.Black));
            this.boardTiles[x][6].hasPiece=true;
        }
        this.boardTiles[0][7].setPiece(new Rook(0,7,PieceColor.Black));
        this.boardTiles[7][7].setPiece(new Rook(7,7,PieceColor.Black));
        this.boardTiles[1][7].setPiece(new Knight(1,7,PieceColor.Black));
        this.boardTiles[6][7].setPiece(new Knight(6,7,PieceColor.Black));
        this.boardTiles[2][7].setPiece(new Bishop(2,7,PieceColor.Black));
        this.boardTiles[5][7].setPiece(new Bishop(5,7,PieceColor.Black));
        this.boardTiles[3][7].setPiece(new Queen(3,7,PieceColor.Black));
        this.boardTiles[4][7].setPiece(blackKing);

        this.boardTiles[0][7].hasPiece=true;
        this.boardTiles[7][7].hasPiece=true;
        this.boardTiles[1][7].hasPiece=true;
        this.boardTiles[6][7].hasPiece=true;
        this.boardTiles[2][7].hasPiece=true;
        this.boardTiles[5][7].hasPiece=true;
        this.boardTiles[3][0].hasPiece=true;
        this.boardTiles[4][7].hasPiece=true;

//        for (int x=0;x<8;x++){
//            for (int y=0;y<8;y++){
//                if (y==2 || y==3 || y==4 || y==5)
//                    this.boardTiles[x][y].hasPiece=true;
//                 else
//                     this.boardTiles[x][y].hasPiece=true;
//            }
//        }
    }

    public void testBoard(){
        for (int x=0;x<8;x++){
            for (int y=0;y<8;y++){
                this.boardTiles[x][y].hasPiece=false;
            }
        }
        this.boardTiles[3][0].setPiece(whiteKing);
        this.boardTiles[3][0].hasPiece=true;

        this.boardTiles[3][7].setPiece(blackKing);
        this.boardTiles[3][7].hasPiece=true;


        this.boardTiles[4][5].setPiece(new Pawn(4,5, PieceColor.White));
        this.boardTiles[4][5].hasPiece=true;
        this.boardTiles[4][2].setPiece(new Pawn(4,2, PieceColor.Black));
        this.boardTiles[4][2].hasPiece=true;

        this.boardTiles[4][3].setPiece(new Rook(4,3, PieceColor.Black));
        this.boardTiles[4][3].hasPiece=true;

        this.boardTiles[3][4].setPiece(new Rook(3,4, PieceColor.White));
        this.boardTiles[3][4].hasPiece=true;

        this.boardTiles[5][0].setPiece(new Bishop(5,0,PieceColor.White));
        this.boardTiles[0][0].setPiece(new Rook(0,0,PieceColor.White));
        this.boardTiles[5][0].hasPiece=true;
        this.boardTiles[0][0].hasPiece=true;
    }

    public void testKingBoard(){
        blackKing=new King(2,6,PieceColor.Black);
        whiteKing=new King(2,2,PieceColor.White);

        for (int x=0;x<8;x++){
            for (int y=0;y<8;y++){
                this.boardTiles[x][y].hasPiece=false;
            }
        }

        this.boardTiles[2][6].setPiece(blackKing);
        this.boardTiles[2][6].hasPiece=true;

        this.boardTiles[2][2].setPiece(whiteKing);
        this.boardTiles[2][2].hasPiece=true;

        this.boardTiles[1][4].setPiece(new Rook(1,4, PieceColor.White));
        this.boardTiles[1][4].hasPiece=true;

        this.boardTiles[3][4].setPiece(new Pawn(3,4, PieceColor.White));
        this.boardTiles[3][4].hasPiece=true;
        this.boardTiles[7][6].setPiece(new Pawn(7,6, PieceColor.White));
        this.boardTiles[7][6].hasPiece=true;

        this.boardTiles[5][2].setPiece(new Pawn(5,2, PieceColor.Black));
        this.boardTiles[5][2].hasPiece=true;

        this.boardTiles[2][7].setPiece(new Rook(2,7, PieceColor.Black));
        this.boardTiles[2][7].hasPiece=true;

        this.boardTiles[6][3].setPiece(new Bishop(6,3,PieceColor.White));
        this.boardTiles[6][3].hasPiece=true;

        this.boardTiles[7][3].setPiece(new Knight(7,3,PieceColor.White));
        this.boardTiles[7][3].hasPiece=true;
//        this.boardTiles[1][5].setPiece(new Knight(1,5,PieceColor.Black));
//        this.boardTiles[1][5].hasPiece=true;


//        blackKing=new King(2,6,PieceColor.Black);
//        whiteKing=new King(2,2,PieceColor.White);
//
//        this.boardTiles[2][7].setPiece(new Rook(2,7,PieceColor.White));
//        this.boardTiles[2][7].hasPiece=true;
//
//        this.boardTiles[2][6].setPiece(blackKing);
//        this.boardTiles[2][6].hasPiece=true;
//
//        this.boardTiles[2][7].setPiece(new Rook(2,7,PieceColor.White));
//        this.boardTiles[2][7].hasPiece=true;
//
//        this.boardTiles[7][6].setPiece(new Pawn(7,6,PieceColor.White));
//        this.boardTiles[7][6].hasPiece=true;
//
//        this.boardTiles[3][5].setPiece(new Pawn(3,5,PieceColor.White));
//        this.boardTiles[3][5].hasPiece=true;
//
//        this.boardTiles[4][4].setPiece(new Rook(4,4,PieceColor.Black));
//        this.boardTiles[4][4].hasPiece=true;
//
//        this.boardTiles[5][4].setPiece(new Knight(2,7,PieceColor.White));
//        this.boardTiles[5][4].hasPiece=true;
//
//        this.boardTiles[6][3].setPiece(new Bishop(2,7,PieceColor.White));
//        this.boardTiles[6][3].hasPiece=true;


    }
}
