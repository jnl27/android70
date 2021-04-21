package com.example.androidchess70;

import androidx.appcompat.app.AppCompatActivity;
import chess.Board;
import pieces.*;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static Board chessBoard = new Board();
    static boolean whiteTurn = true;
    public TextView[][] displayBoard = new TextView[8][8];
    public TextView[][] displayBoardBg = new TextView[8][8];
    public TextView gameOver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}