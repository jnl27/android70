package com.example.androidchess70;

import GameRecording.GameSave;
import GameRecording.LoadSaveData;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SaveGameScreen extends AppCompatActivity {

    private Button save;
    private Button cancel;
    private EditText name;
    private ArrayList<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_game_screen);

         save = (Button)findViewById(R.id.saveButton);
         cancel = (Button)findViewById(R.id.cancelButton);
         name=(EditText) findViewById(R.id.gameName);
        // list=new ArrayList<>();
         String gameWinner = getIntent().getExtras().getString("winner");
         list = getIntent().getStringArrayListExtra("moves");


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(name.getText().toString() == null || name.getText().toString().length()==0){
                    Toast.makeText(SaveGameScreen.this,"Name field can't be empty",Toast.LENGTH_LONG).show();
                    return;
                }

                GameSave newGameData = new GameSave(name.getText().toString(),gameWinner,list);
                if(LoadSaveData.list == null || LoadSaveData.list.size()==0) {
                    LoadSaveData.list = new ArrayList<GameSave>();
                }
                LoadSaveData.list.add(newGameData);
                try {
                    LoadSaveData.saveGameData();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(SaveGameScreen.this, HomePage.class);
                startActivity(intent);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SaveGameScreen.this, HomePage.class);
                startActivity(intent);
            }
        });


    }
}