package com.example.androidchess70;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

public class GamePlaybackActivity extends AppCompatActivity {
    public ArrayList<String> movesToExecute;
    private Button nextMoveButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_playback);
        movesToExecute = getIntent().getStringArrayListExtra("moves");
        nextMoveButton=(Button)findViewById(R.id.nextMove);


    }
}