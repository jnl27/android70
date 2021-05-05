package com.example.androidchess70;

import androidx.appcompat.app.AppCompatActivity;
import chess.Board;
import chess.Spot;
import pieces.Bishop;
import pieces.ChessPiece;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Queen;
import pieces.Rook;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GamePlaybackActivity extends AppCompatActivity {
    public ArrayList<String> movesToExecute;
    private Button nextMoveButton;
    public static Board chessBoard;
    public static boolean whiteTurn;
    public TextView[][] displayBoard;
    public TextView gameOver;
    int moveNumber;
    public TextView[][] displayBoardBg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_playback);
        whiteTurn=true;
        moveNumber=1;
        gameOver=(TextView)findViewById(R.id.gameOver);
        displayBoard = new TextView[8][8];
        displayBoardBg = new TextView[8][8];
       // gameOver();

        chessBoard = new Board();
        Intent intent=getIntent();
        movesToExecute = intent.getStringArrayListExtra("moves");
      /*  movesToExecute.add("16,14");
        movesToExecute.add("71,73");
        movesToExecute.add("14,13");
        movesToExecute.add("21,23");
        movesToExecute.add("13,22,23");

        movesToExecute.add("73,74");*/

        setUpBoardViews();
        nextMoveButton=(Button)findViewById(R.id.nextMove);


        nextMoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (moveNumber>movesToExecute.size()) {
                    Toast.makeText(GamePlaybackActivity.this,"No more moves left!",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(GamePlaybackActivity.this,HomePage.class);
                    startActivity(intent);
                }
                else {

                    String move=movesToExecute.get(moveNumber-1);

                    String[] split=move.split(","); //46,44,
                    if (split.length==2) {
                        String start = split[0];
                        String end = split[1];

                        int startingX = Character.getNumericValue(start.charAt(0));
                        int startingY = Character.getNumericValue(start.charAt(1));
                        int endingX = Character.getNumericValue(end.charAt(0));
                        int endingY = Character.getNumericValue(end.charAt(1));


                        ChessPiece mover = chessBoard.grid[startingX][startingY].getPiece();
                        Spot destSpot = chessBoard.grid[endingX][endingY];
                        destSpot.setPiece(mover);
                        displayBoard[endingX][endingY].setBackgroundResource(getResource(mover));
                        chessBoard.grid[startingX][startingY].setPiece(null);
                        displayBoard[startingX][startingY].setBackgroundResource(0);
                    }
                    else if (split.length==3){
                        if (split[2].length()==1) {
                            String start = split[0];
                            String end = split[1];
                            String piece=split[2];

                            int startingX = Character.getNumericValue(start.charAt(0));

                            int startingY = Character.getNumericValue(start.charAt(1));

                            int endingX = Character.getNumericValue(end.charAt(0));
                            int endingY = Character.getNumericValue(end.charAt(1));

                            if (piece.equals("Q")){
                                if (whiteTurn) {
                                    displayBoard[endingX][endingY].setBackgroundResource(R.drawable.wqueen);
                                }
                                else{
                                    displayBoard[endingX][endingY].setBackgroundResource(R.drawable.bqueen);
                                }
                            }
                            else if (piece.equals("B")){
                                if (whiteTurn) {
                                    displayBoard[endingX][endingY].setBackgroundResource(R.drawable.wbishop);
                                }
                                else{
                                    displayBoard[endingX][endingY].setBackgroundResource(R.drawable.bbishop);
                                }
                            }
                            else if (piece.equals("R")){
                                if (whiteTurn) {
                                    displayBoard[endingX][endingY].setBackgroundResource(R.drawable.wrook);
                                }
                                else{
                                    displayBoard[endingX][endingY].setBackgroundResource(R.drawable.brook);
                                }
                            }
                            else if (piece.equals("K")){
                                if (whiteTurn) {
                                    displayBoard[endingX][endingY].setBackgroundResource(R.drawable.wknight);
                                }
                                else{
                                    displayBoard[endingX][endingY].setBackgroundResource(R.drawable.bknight);
                                }
                            }

                          //  ChessPiece mover = chessBoard.grid[startingX][startingY].getPiece();
                           // Spot destSpot = chessBoard.grid[endingX][endingY];
                          //  destSpot.setPiece(mover);
                           // displayBoard[endingX][endingY].setBackgroundResource(getResource(mover));
                            chessBoard.grid[startingX][startingY].setPiece(null);
                            displayBoard[startingX][startingY].setBackgroundResource(0);
                        }
                        else if (split[2].length()==2){  //enpassant
                            String start = split[0];
                            String end = split[1];
                            String pieceToDestroy=split[2];

                            int startingX = Character.getNumericValue(start.charAt(0));
                            int startingY = Character.getNumericValue(start.charAt(1));
                            int endingX = Character.getNumericValue(end.charAt(0));
                            int endingY = Character.getNumericValue(end.charAt(1));
                            int destroyX=Character.getNumericValue(pieceToDestroy.charAt(0));
                            int destroyY=Character.getNumericValue(pieceToDestroy.charAt(1));

                            ChessPiece mover = chessBoard.grid[startingX][startingY].getPiece();
                            Spot destSpot = chessBoard.grid[endingX][endingY];
                            destSpot.setPiece(mover);
                            displayBoard[endingX][endingY].setBackgroundResource(getResource(mover));
                            chessBoard.grid[startingX][startingY].setPiece(null);
                            displayBoard[startingX][startingY].setBackgroundResource(0);
                            chessBoard.grid[destroyX][destroyY].setPiece(null);
                            displayBoard[destroyX][destroyY].setBackgroundResource(0);

                        }

                    }
                    else if (split.length==4){   //castling
                        String kingStart = split[0];
                        String kingEnd = split[1];
                        String rookStart=split[2];
                        String rookEnd=split[3];

                        int startingKingX = Character.getNumericValue(kingStart.charAt(0));
                        int startingKingY = Character.getNumericValue(kingStart.charAt(1));
                        int endingKingX = Character.getNumericValue(kingEnd.charAt(0));
                        int endingKingY = Character.getNumericValue(kingEnd.charAt(1));

                        int startingRookX = Character.getNumericValue(rookStart.charAt(0));
                        int startingRookY = Character.getNumericValue(rookStart.charAt(1));
                        int endingRookX = Character.getNumericValue(rookEnd.charAt(0));
                        int endingRookY = Character.getNumericValue(rookEnd.charAt(1));

                        ChessPiece mover = chessBoard.grid[startingKingX][startingKingY].getPiece();
                        Spot destSpot = chessBoard.grid[endingKingX][endingKingY];
                        destSpot.setPiece(mover);
                        displayBoard[endingKingX][endingKingY].setBackgroundResource(getResource(mover));
                        chessBoard.grid[startingKingX][startingKingY].setPiece(null);
                        displayBoard[startingKingX][startingKingY].setBackgroundResource(0);

                        ChessPiece newMover = chessBoard.grid[startingRookX][startingRookY].getPiece();
                        Spot newDestSpot = chessBoard.grid[endingRookX][endingRookY];
                        newDestSpot.setPiece(newMover);
                        displayBoard[endingRookX][endingRookY].setBackgroundResource(getResource(newMover));
                        chessBoard.grid[startingRookX][startingRookY].setPiece(null);
                        displayBoard[startingRookX][startingRookY].setBackgroundResource(0);
                    }


                }
                whiteTurn = whiteTurn ? false : true;
                if (moveNumber==movesToExecute.size()){
                    gameOver.setText("Game Over!!");
                    gameOver.setVisibility(View.VISIBLE);
                }
                moveNumber++;

            }
        });
        gameOver();

    }
    private void setUpBoardViews() {
        chessBoard.makeStartBoard();
        displayBoard[0][0] = (TextView) findViewById(R.id.F00);
        displayBoardBg[0][0] = (TextView) findViewById(R.id.bg00);
        displayBoard[1][0] = (TextView) findViewById(R.id.F10);
        displayBoardBg[1][0] = (TextView) findViewById(R.id.bg10);
        displayBoard[2][0] = (TextView) findViewById(R.id.F20);
        displayBoardBg[2][0] = (TextView) findViewById(R.id.bg20);
        displayBoard[3][0] = (TextView) findViewById(R.id.F30);
        displayBoardBg[3][0] = (TextView) findViewById(R.id.bg30);
        displayBoard[4][0] = (TextView) findViewById(R.id.F40);
        displayBoardBg[4][0] = (TextView) findViewById(R.id.bg40);
        displayBoard[5][0] = (TextView) findViewById(R.id.F50);
        displayBoardBg[5][0] = (TextView) findViewById(R.id.bg50);
        displayBoard[6][0] = (TextView) findViewById(R.id.F60);
        displayBoardBg[6][0] = (TextView) findViewById(R.id.bg60);
        displayBoard[7][0] = (TextView) findViewById(R.id.F70);
        displayBoardBg[7][0] = (TextView) findViewById(R.id.bg70);

        displayBoard[0][1] = (TextView) findViewById(R.id.F01);
        displayBoardBg[0][1] = (TextView) findViewById(R.id.bg01);
        displayBoard[1][1] = (TextView) findViewById(R.id.F11);
        displayBoardBg[1][1] = (TextView) findViewById(R.id.bg11);
        displayBoard[2][1] = (TextView) findViewById(R.id.F21);
        displayBoardBg[2][1] = (TextView) findViewById(R.id.bg21);
        displayBoard[3][1] = (TextView) findViewById(R.id.F31);
        displayBoardBg[3][1] = (TextView) findViewById(R.id.bg31);
        displayBoard[4][1] = (TextView) findViewById(R.id.F41);
        displayBoardBg[4][1] = (TextView) findViewById(R.id.bg41);
        displayBoard[5][1] = (TextView) findViewById(R.id.F51);
        displayBoardBg[5][1] = (TextView) findViewById(R.id.bg51);
        displayBoard[6][1] = (TextView) findViewById(R.id.F61);
        displayBoardBg[6][1] = (TextView) findViewById(R.id.bg61);
        displayBoard[7][1] = (TextView) findViewById(R.id.F71);
        displayBoardBg[7][1] = (TextView) findViewById(R.id.bg71);

        displayBoard[0][2] = (TextView) findViewById(R.id.F02);
        displayBoardBg[0][2] = (TextView) findViewById(R.id.bg02);
        displayBoard[1][2] = (TextView) findViewById(R.id.F12);
        displayBoardBg[1][2] = (TextView) findViewById(R.id.bg12);
        displayBoard[2][2] = (TextView) findViewById(R.id.F22);
        displayBoardBg[2][2] = (TextView) findViewById(R.id.bg22);
        displayBoard[3][2] = (TextView) findViewById(R.id.F32);
        displayBoardBg[3][2] = (TextView) findViewById(R.id.bg32);
        displayBoard[4][2] = (TextView) findViewById(R.id.F42);
        displayBoardBg[4][2] = (TextView) findViewById(R.id.bg42);
        displayBoard[5][2] = (TextView) findViewById(R.id.F52);
        displayBoardBg[5][2] = (TextView) findViewById(R.id.bg52);
        displayBoard[6][2] = (TextView) findViewById(R.id.F62);
        displayBoardBg[6][2] = (TextView) findViewById(R.id.bg62);
        displayBoard[7][2] = (TextView) findViewById(R.id.F72);
        displayBoardBg[7][2] = (TextView) findViewById(R.id.bg72);

        displayBoard[0][3] = (TextView) findViewById(R.id.F03);
        displayBoardBg[0][3] = (TextView) findViewById(R.id.bg03);
        displayBoard[1][3] = (TextView) findViewById(R.id.F13);
        displayBoardBg[1][3] = (TextView) findViewById(R.id.bg13);
        displayBoard[2][3] = (TextView) findViewById(R.id.F23);
        displayBoardBg[2][3] = (TextView) findViewById(R.id.bg23);
        displayBoard[3][3] = (TextView) findViewById(R.id.F33);
        displayBoardBg[3][3] = (TextView) findViewById(R.id.bg33);
        displayBoard[4][3] = (TextView) findViewById(R.id.F43);
        displayBoardBg[4][3] = (TextView) findViewById(R.id.bg43);
        displayBoard[5][3] = (TextView) findViewById(R.id.F53);
        displayBoardBg[5][3] = (TextView) findViewById(R.id.bg53);
        displayBoard[6][3] = (TextView) findViewById(R.id.F63);
        displayBoardBg[6][3] = (TextView) findViewById(R.id.bg63);
        displayBoard[7][3] = (TextView) findViewById(R.id.F73);
        displayBoardBg[7][3] = (TextView) findViewById(R.id.bg73);

        displayBoard[0][4] = (TextView) findViewById(R.id.F04);
        displayBoardBg[0][4] = (TextView) findViewById(R.id.bg04);
        displayBoard[1][4] = (TextView) findViewById(R.id.F14);
        displayBoardBg[1][4] = (TextView) findViewById(R.id.bg14);
        displayBoard[2][4] = (TextView) findViewById(R.id.F24);
        displayBoardBg[2][4] = (TextView) findViewById(R.id.bg24);
        displayBoard[3][4] = (TextView) findViewById(R.id.F34);
        displayBoardBg[3][4] = (TextView) findViewById(R.id.bg34);
        displayBoard[4][4] = (TextView) findViewById(R.id.F44);
        displayBoardBg[4][4] = (TextView) findViewById(R.id.bg44);
        displayBoard[5][4] = (TextView) findViewById(R.id.F54);
        displayBoardBg[5][4] = (TextView) findViewById(R.id.bg54);
        displayBoard[6][4] = (TextView) findViewById(R.id.F64);
        displayBoardBg[6][4] = (TextView) findViewById(R.id.bg64);
        displayBoard[7][4] = (TextView) findViewById(R.id.F74);
        displayBoardBg[7][4] = (TextView) findViewById(R.id.bg74);

        displayBoard[0][5] = (TextView) findViewById(R.id.F05);
        displayBoardBg[0][5] = (TextView) findViewById(R.id.bg05);
        displayBoard[1][5] = (TextView) findViewById(R.id.F15);
        displayBoardBg[1][5] = (TextView) findViewById(R.id.bg15);
        displayBoard[2][5] = (TextView) findViewById(R.id.F25);
        displayBoardBg[2][5] = (TextView) findViewById(R.id.bg25);
        displayBoard[3][5] = (TextView) findViewById(R.id.F35);
        displayBoardBg[3][5] = (TextView) findViewById(R.id.bg35);
        displayBoard[4][5] = (TextView) findViewById(R.id.F45);
        displayBoardBg[4][5] = (TextView) findViewById(R.id.bg45);
        displayBoard[5][5] = (TextView) findViewById(R.id.F55);
        displayBoardBg[5][5] = (TextView) findViewById(R.id.bg55);
        displayBoard[6][5] = (TextView) findViewById(R.id.F65);
        displayBoardBg[6][5] = (TextView) findViewById(R.id.bg65);
        displayBoard[7][5] = (TextView) findViewById(R.id.F75);
        displayBoardBg[7][5] = (TextView) findViewById(R.id.bg75);

        displayBoard[0][6] = (TextView) findViewById(R.id.F06);
        displayBoardBg[0][6] = (TextView) findViewById(R.id.bg06);
        displayBoard[1][6] = (TextView) findViewById(R.id.F16);
        displayBoardBg[1][6] = (TextView) findViewById(R.id.bg16);
        displayBoard[2][6] = (TextView) findViewById(R.id.F26);
        displayBoardBg[2][6] = (TextView) findViewById(R.id.bg26);
        displayBoard[3][6] = (TextView) findViewById(R.id.F36);
        displayBoardBg[3][6] = (TextView) findViewById(R.id.bg36);
        displayBoard[4][6] = (TextView) findViewById(R.id.F46);
        displayBoardBg[4][6] = (TextView) findViewById(R.id.bg46);
        displayBoard[5][6] = (TextView) findViewById(R.id.F56);
        displayBoardBg[5][6] = (TextView) findViewById(R.id.bg56);
        displayBoard[6][6] = (TextView) findViewById(R.id.F66);
        displayBoardBg[6][6] = (TextView) findViewById(R.id.bg66);
        displayBoard[7][6] = (TextView) findViewById(R.id.F76);
        displayBoardBg[7][6] = (TextView) findViewById(R.id.bg76);

        displayBoard[0][7] = (TextView) findViewById(R.id.F07);
        displayBoardBg[0][7] = (TextView) findViewById(R.id.bg07);
        displayBoard[1][7] = (TextView) findViewById(R.id.F17);
        displayBoardBg[1][7] = (TextView) findViewById(R.id.bg17);
        displayBoard[2][7] = (TextView) findViewById(R.id.F27);
        displayBoardBg[2][7] = (TextView) findViewById(R.id.bg27);
        displayBoard[3][7] = (TextView) findViewById(R.id.F37);
        displayBoardBg[3][7] = (TextView) findViewById(R.id.bg37);
        displayBoard[4][7] = (TextView) findViewById(R.id.F47);
        displayBoardBg[4][7] = (TextView) findViewById(R.id.bg47);
        displayBoard[5][7] = (TextView) findViewById(R.id.F57);
        displayBoardBg[5][7] = (TextView) findViewById(R.id.bg57);
        displayBoard[6][7] = (TextView) findViewById(R.id.F67);
        displayBoardBg[6][7] = (TextView) findViewById(R.id.bg67);
        displayBoard[7][7] = (TextView) findViewById(R.id.F77);
        displayBoardBg[7][7] = (TextView) findViewById(R.id.bg77);

        for (int i=0; i<8; i++){ //colors switch in original setup, lazy fix
            for (int j=0; j<8; j++){
                if ((i+j)%2 == 0){
                    displayBoardBg[j][i].setBackgroundResource(R.color.colorBoardLight);
                }else {
                    displayBoardBg[j][i].setBackgroundResource(R.color.colorBoardDark);
                }
            }
        }
        placePieces();
    }

    private void placePieces() {
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                ChessPiece currPiece = chessBoard.grid[i][j].getPiece();
                if (currPiece != null) {
                    if (currPiece instanceof King) {
                        if (currPiece.getColor() == 0) { //white
                            displayBoard[i][j].setBackgroundResource(R.drawable.wking);
                        } else {
                            displayBoard[i][j].setBackgroundResource(R.drawable.bking);
                        }
                    } else if (currPiece instanceof Queen) {
                        if (currPiece.getColor() == 0) { //white
                            displayBoard[i][j].setBackgroundResource(R.drawable.wqueen);
                        } else {
                            displayBoard[i][j].setBackgroundResource(R.drawable.bqueen);
                        }
                    } else if (currPiece instanceof Bishop) {
                        if (currPiece.getColor() == 0) { //white
                            displayBoard[i][j].setBackgroundResource(R.drawable.wbishop);
                        } else {
                            displayBoard[i][j].setBackgroundResource(R.drawable.bbishop);
                        }
                    } else if (currPiece instanceof Knight) {
                        if (currPiece.getColor() == 0) { //white
                            displayBoard[i][j].setBackgroundResource(R.drawable.wknight);
                        } else {
                            displayBoard[i][j].setBackgroundResource(R.drawable.bknight);
                        }
                    } else if (currPiece instanceof Rook) {
                        if (currPiece.getColor() == 0) { //white
                            displayBoard[i][j].setBackgroundResource(R.drawable.wrook);
                        } else {
                            displayBoard[i][j].setBackgroundResource(R.drawable.brook);
                        }
                    } else if (currPiece instanceof Pawn) {
                        if (currPiece.getColor() == 0) { //white
                            displayBoard[i][j].setBackgroundResource(R.drawable.wpawn);
                        } else {
                            displayBoard[i][j].setBackgroundResource(R.drawable.bpawn);
                        }
                    }
                }else{
                    displayBoard[i][j].setBackgroundResource(0);
                }
            }
        }
    }
    private int getResource(ChessPiece piece) {
        if (whiteTurn){
            if (piece instanceof Pawn){
                return R.drawable.wpawn;
            }else if (piece instanceof Rook){
                return R.drawable.wrook;
            }else if (piece instanceof Knight){
                return R.drawable.wknight;
            }else if (piece instanceof Bishop){
                return R.drawable.wbishop;
            }else if (piece instanceof Queen){
                return R.drawable.wqueen;
            }else if (piece instanceof King){
                return R.drawable.wking;
            }else{
                return 0;
            }
        }else{
            if (piece instanceof Pawn){
                return R.drawable.bpawn;
            }else if (piece instanceof Rook){
                return R.drawable.brook;
            }else if (piece instanceof Knight){
                return R.drawable.bknight;
            }else if (piece instanceof Bishop){
                return R.drawable.bbishop;
            }else if (piece instanceof Queen){
                return R.drawable.bqueen;
            }else if (piece instanceof King){
                return R.drawable.bking;
            }else{
                return 0;
            }
        }
    }
    public void gameOver() {
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                displayBoard[j][i].setClickable(false);
            }
        }
    }
}