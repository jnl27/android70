package com.example.androidchess70;

import androidx.appcompat.app.AppCompatActivity;
import chess.*;
import pieces.*;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static chess.Chess.findKingPosition;
import static chess.Chess.isCheckMate;
import static chess.Chess.isKingInCheck;
import static chess.Chess.setUpGame;
import static pieces.King.castledK;
import static pieces.King.castledQ;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static Board chessBoard = new Board();
    static boolean whiteTurn = true;
    public TextView[][] displayBoard = new TextView[8][8];
    public TextView[][] displayBoardBg = new TextView[8][8];
    public Spot clickedSpot = chessBoard.grid[0][0];
    public Spot fromSpot = chessBoard.grid[0][0];
    public boolean firstClick = true;
    public int numMoves = 0;
    public ArrayList<Spot> prevMoves = new ArrayList<>();
    public TextView gameOver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpBoardViews();

        gameOver = findViewById(R.id.gameOver);
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

        placePieces();
    }

    private void placePieces(){
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
    @Override
    public void onClick(View v){
        Log.d("ChessApp", "CLICKED" + v.getId());
        Context context = getApplicationContext();
        Toast newToast = Toast.makeText(context, v.getId()+"!", Toast.LENGTH_SHORT);
        /*
        switch (v.getId()){
            case R.id.F00:
                clickedSpot = chessBoard.grid[0][0];
                break;
            case R.id.F10:
                clickedSpot = chessBoard.grid[1][0];
                break;
            case R.id.F20:
                clickedSpot = chessBoard.grid[2][0];
                break;
            case R.id.F30:
                clickedSpot = chessBoard.grid[3][0];
                break;
            case R.id.F40:
                clickedSpot = chessBoard.grid[4][0];
                break;
            case R.id.F50:
                clickedSpot = chessBoard.grid[5][0];
                break;
            case R.id.F60:
                clickedSpot = chessBoard.grid[6][0];
                break;
            case R.id.F70:
                clickedSpot = chessBoard.grid[7][0];
                break;
            case R.id.F01:
                clickedSpot = chessBoard.grid[0][1];
                break;
            case R.id.F11:
                clickedSpot = chessBoard.grid[1][1];
                break;
            case R.id.F21:
                clickedSpot = chessBoard.grid[2][1];
                break;
            case R.id.F31:
                clickedSpot = chessBoard.grid[3][1];
                break;
            case R.id.F41:
                clickedSpot = chessBoard.grid[4][1];
                break;
            case R.id.F51:
                 clickedSpot = chessBoard.grid[5][1];
                break;
            case R.id.F61:
                clickedSpot = chessBoard.grid[6][1];
                break;
            case R.id.F71:
                clickedSpot = chessBoard.grid[7][1];
                break;
            case R.id.F02:
                clickedSpot = chessBoard.grid[0][2];
                break;
            case R.id.F12:
                clickedSpot = chessBoard.grid[1][2];
                break;
            case R.id.F22:
                clickedSpot = chessBoard.grid[2][2];
                break;
            case R.id.F32:
                clickedSpot = chessBoard.grid[3][2];
                break;
            case R.id.F42:
                clickedSpot = chessBoard.grid[4][2];
                break;
            case R.id.F52:
                clickedSpot = chessBoard.grid[5][2];
                break;
            case R.id.F62:
                clickedSpot = chessBoard.grid[6][2];
                break;
            case R.id.F72:
                clickedSpot = chessBoard.grid[7][2];
                break;
            case R.id.F03:
                clickedSpot = chessBoard.grid[0][3];
                break;
            case R.id.F13:
                clickedSpot = chessBoard.grid[1][3];
                break;
            case R.id.F23:
                clickedSpot = chessBoard.grid[2][3];
                break;
            case R.id.F33:
                clickedSpot = chessBoard.grid[3][3];
                break;
            case R.id.F43:
                clickedSpot = chessBoard.grid[4][3];
                break;
            case R.id.F53:
                clickedSpot = chessBoard.grid[5][3];
                break;
            case R.id.F63:
                clickedSpot = chessBoard.grid[6][3];
                break;
            case R.id.F73:
                clickedSpot = chessBoard.grid[7][3];
                break;
            case R.id.F04:
                clickedSpot = chessBoard.grid[0][4];
                break;
            case R.id.F14:
                clickedSpot = chessBoard.grid[1][4];
                break;
            case R.id.F24:
                clickedSpot = chessBoard.grid[2][4];
                break;
            case R.id.F34:
                clickedSpot = chessBoard.grid[3][4];
                break;
            case R.id.F44:
                clickedSpot = chessBoard.grid[4][4];
                break;
            case R.id.F54:
                clickedSpot = chessBoard.grid[5][4];
                break;
            case R.id.F64:
                clickedSpot = chessBoard.grid[6][4];
                break;
            case R.id.F74:
                clickedSpot = chessBoard.grid[7][4];
                break;
            case R.id.F05:
                clickedSpot = chessBoard.grid[0][5];
                break;
            case R.id.F15:
                clickedSpot = chessBoard.grid[1][5];
                break;
            case R.id.F25:
                clickedSpot = chessBoard.grid[2][5];
                break;
            case R.id.F35:
                clickedSpot = chessBoard.grid[3][5];
                break;
            case R.id.F45:
                clickedSpot = chessBoard.grid[4][5];
                break;
            case R.id.F55:
                clickedSpot = chessBoard.grid[5][5];
                break;
            case R.id.F65:
                clickedSpot = chessBoard.grid[6][5];
                break;
            case R.id.F75:
                clickedSpot = chessBoard.grid[7][5];
                break;
            case R.id.F06:
                clickedSpot = chessBoard.grid[0][6];
                break;
            case R.id.F16:
                clickedSpot = chessBoard.grid[1][6];
                break;
            case R.id.F26:
                clickedSpot = chessBoard.grid[2][6];
                break;
            case R.id.F36:
                clickedSpot = chessBoard.grid[3][6];
                break;
            case R.id.F46:
                clickedSpot = chessBoard.grid[4][6];
                break;
            case R.id.F56:
                clickedSpot = chessBoard.grid[5][6];
                break;
            case R.id.F66:
                clickedSpot = chessBoard.grid[6][6];
                break;
            case R.id.F76:
                clickedSpot = chessBoard.grid[7][6];
                break;
            case R.id.F07:
                clickedSpot = chessBoard.grid[0][7];
                break;
            case R.id.F17:
                clickedSpot = chessBoard.grid[1][7];
                break;
            case R.id.F27:
                clickedSpot = chessBoard.grid[2][7];
                break;
            case R.id.F37:
                clickedSpot = chessBoard.grid[3][7];
                break;
            case R.id.F47:
                clickedSpot = chessBoard.grid[4][7];
                break;
            case R.id.F57:
                clickedSpot = chessBoard.grid[5][7];
                break;
            case R.id.F67:
                clickedSpot = chessBoard.grid[6][7];
                break;
            case R.id.F77:
                clickedSpot = chessBoard.grid[7][7];
                break;
        }
        int currColor = whiteTurn ? 0 : 1;

        if (whiteTurn) {
            //System.out.println();
            boolean check=isKingInCheck(0,findKingPosition(0));
            Spot kingSpot = findKingPosition(0);
            if (check){
                displayBoardBg[kingSpot.getXCoordinate()][kingSpot.getYCoordinate()].setBackgroundResource(R.color.colorKingInDanger);
                boolean checkmate=isCheckMate(0);
                if (checkmate) {
                    gameOver.setText("White Wins!");
                    gameOver.setVisibility(View.VISIBLE);
                }
                else {
                    System.out.println("Check");
                }
            }else {
                if ((kingSpot.getXCoordinate() + kingSpot.getYCoordinate()) % 2 == 0) {
                    displayBoardBg[kingSpot.getXCoordinate()][kingSpot.getYCoordinate()].setBackgroundResource(R.color.colorBoardDark);
                } else {
                    displayBoardBg[kingSpot.getXCoordinate()][kingSpot.getYCoordinate()].setBackgroundResource(R.color.colorBoardLight);
                }
            }
            System.out.print("White's move: ");

        }
        else {
            System.out.println();
            boolean check=isKingInCheck(1,findKingPosition(1));
            Spot kingSpot = findKingPosition(1);
            if (check){
                displayBoardBg[kingSpot.getXCoordinate()][kingSpot.getYCoordinate()].setBackgroundResource(R.color.colorKingInDanger);
                boolean checkmate=isCheckMate(1);
                if (checkmate) {
                    gameOver.setText("Black Wins!");
                    gameOver.setVisibility(View.VISIBLE);
                }
                else {
                    System.out.println("Check");
                }
            }else {
                if ((kingSpot.getXCoordinate() + kingSpot.getYCoordinate()) % 2 == 0) {
                    displayBoardBg[kingSpot.getXCoordinate()][kingSpot.getYCoordinate()].setBackgroundResource(R.color.colorBoardDark);
                } else {
                    displayBoardBg[kingSpot.getXCoordinate()][kingSpot.getYCoordinate()].setBackgroundResource(R.color.colorBoardLight);
                }
            }
            System.out.print("Black's move: ");
        }
        if (firstClick){
            if (chessBoard.grid[clickedSpot.getXCoordinate()][clickedSpot.getYCoordinate()].getPiece() == null) { //do nothing, invalid selection
                return;
            }else if (chessBoard.grid[clickedSpot.getXCoordinate()][clickedSpot.getYCoordinate()].getPiece().getColor() != currColor) {//wrong turn, do nothing
                return;
            }else{ //valid selection
                displayBoardBg[clickedSpot.getXCoordinate()][clickedSpot.getYCoordinate()].setBackgroundResource(R.color.colorSelected);
                firstClick = false;
                fromSpot = clickedSpot;
            }
        }else{ //second selection
            if (clickedSpot.isEmpty()){
                if (fromSpot.getPiece().validMoveWithoutCheck(chessBoard, fromSpot, clickedSpot) && chessBoard.isPathEmpty(fromSpot, clickedSpot)){ //valid move to empty space
                    boolean pawnPromo = false;
                    boolean enPassant = false;
                    //valid move
                    //CASTLING
                    if (castledK) {
                        if (whiteTurn){
                            //move rook only as king moving is handled below
                            chessBoard.grid[5][7].setPiece(chessBoard.grid[7][7].getPiece());
                            displayBoard[5][7].setBackgroundResource(R.drawable.wrook);
                            chessBoard.grid[7][7].setPiece(null);
                            displayBoard[7][7].setBackgroundResource(0);
                        }else{
                            chessBoard.grid[5][0].setPiece(chessBoard.grid[7][0].getPiece());
                            displayBoard[5][0].setBackgroundResource(R.drawable.brook);
                            chessBoard.grid[7][0].setPiece(null);
                            displayBoard[7][0].setBackgroundResource(0);
                        }
                        castledK = false;
                    }
                    if (castledQ) {
                        if (whiteTurn){
                            //same thing move rook only
                            chessBoard.grid[3][7].setPiece(chessBoard.grid[0][7].getPiece());
                            displayBoard[3][7].setBackgroundResource(R.drawable.wrook);
                            chessBoard.grid[0][7].setPiece(null);
                            displayBoard[0][7].setBackgroundResource(0);
                        }else{
                            chessBoard.grid[3][0].setPiece(chessBoard.grid[0][0].getPiece());
                            displayBoard[3][0].setBackgroundResource(R.drawable.brook);
                            chessBoard.grid[0][0].setPiece(null);
                            displayBoard[0][0].setBackgroundResource(0);
                        }
                        castledQ = false;
                    }
                    //remove piece from old Spot

                    //System.out.println("selected piece and color: " + curr piece.getPieceName() + " " + curr piece.getColor());
                    ChessPiece mover = fromSpot.getPiece();
                    //System.out.println("mover's previous y change: " + mover.getPreviousChange());
                    ChessPiece destPiece=clickedSpot.getPiece();
                    int xto = clickedSpot.getXCoordinate();
                    int yto = clickedSpot.getYCoordinate();
                    int xfrom = fromSpot.getXCoordinate();
                    int yfrom = fromSpot.getYCoordinate();

                    if (mover instanceof Pawn) { //pawn promo potential or enpassant
                        Pawn currPawn = (Pawn) mover;
                        if (whiteTurn) {
                            if (fromSpot.getYCoordinate() == 1){
                                //WHITE PAWN PROMO dialog
                                //	System.out.println("white's pawn has been promoted to " + toPromo);
                                pawnPromo = true;
                            }else if (currPawn.getEnPassant()){
                                enPassant = true;
                                //System.out.println("\nBAM! " + chessBoard.grid[xto][yto+1].getPiece().getPieceName() + " was enPassant captured by " + mover.getPieceName() + " @ (" + xto + ", " + (yto-1) +")");
                                chessBoard.grid[xto][yto+1].setPiece(null);
                                displayBoard[xto][yto+1].setBackgroundResource(0);
                                clickedSpot.setPiece(mover);
                                displayBoard[xto][yto].setBackgroundResource(R.drawable.wpawn);
                            }
                        }else{
                            if (fromSpot.getYCoordinate() == 6){
                                //BLACK PAWN PROMO DIALOG
                                //	System.out.println("black's pawn has been promoted to " + toPromo);
                                pawnPromo = true;
                            }else if (currPawn.getEnPassant()){
                                enPassant = true;
                                //System.out.println("\nBAM! " +chessBoard.grid[xto][yto-1].getPiece().getPieceName() + " was enPassant captured by " + mover.getPieceName() + " @ (" + xto + ", " + (yto+1) +")");
                                chessBoard.grid[xto][yto-1].setPiece(null);
                                displayBoard[xto][yto-1].setBackgroundResource(0);
                                clickedSpot.setPiece(mover);
                                displayBoard[xto][yto].setBackgroundResource(R.drawable.bpawn);
                            }
                        }
                    }

                    if (!pawnPromo && !enPassant) {
                        //check new Spot for enemy Piece, if so then remove
                        if (clickedSpot.isEmpty()){
                            //System.out.println("\nBAM! " + dest piece.getPieceName() + " was captured by " + mover.getPieceName() + " @ (" + xto + ", " + yto +")");
                            //this was empty lol idk what goes here or if we need this
                        }
                        clickedSpot.setPiece(mover);
                        displayBoard[xto][yto].setBackgroundResource(getResource(mover));
                        fromSpot.setPiece(null);
                        displayBoard[xfrom][yfrom].setBackgroundResource(0);
                        //System.out.println("moved piece has previous y change of " + dest piece.getPreviousChange());
                        if (whiteTurn && isKingInCheck(0,findKingPosition(0))) {
                            System.out.println("Illegal move, try again");
                            fromSpot.setPiece(mover);
                            displayBoard[xfrom][yfrom].setBackgroundResource(getResource(mover));
                            clickedSpot.setPiece(destPiece);
                            displayBoard[xto][yto].setBackgroundResource(getResource(destPiece));
                            return;
                        }
                        else if (!whiteTurn && isKingInCheck(1,findKingPosition(1))) {
                            System.out.println("Illegal move, try again");
                            fromSpot.setPiece(mover);
                            displayBoard[xfrom][yfrom].setBackgroundResource(getResource(mover));
                            clickedSpot.setPiece(destPiece);
                            displayBoard[xto][yto].setBackgroundResource(getResource(destPiece));
                            return;
                        }

                    }
                    fromSpot.setPiece(null);
                    displayBoard[xfrom][yfrom].setBackgroundResource(0);
                    //clear color selection
                    if ((xfrom + yfrom)%2 == 0) {
                        displayBoardBg[xfrom][yfrom].setBackgroundResource(R.color.colorBoardDark);
                    }else {
                        displayBoardBg[xfrom][yfrom].setBackgroundResource(R.color.colorBoardLight);
                    }
                    firstClick = true;
                    whiteTurn = whiteTurn ? false : true; //switch colors

                }
            }
        }*/
    }
    private int getResource(ChessPiece piece){
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
}