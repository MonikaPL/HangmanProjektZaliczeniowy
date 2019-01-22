package com.example.monika.wisielec_projektzaliczeniowy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.widget.TextView;
import android.widget.Toast;


public class ScoreClass extends Activity {
    TextView ScorePlayer1;
    TextView ScorePlayer2;
    int Score1;
    int Score2;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score);

        final Intent intent = getIntent();
        /*
        Score1 = intent.getIntExtra("ScorePlayer1",0);
        Score2 = intent.getIntExtra("ScorePlayer2",0);
        */
        Score1 = TwoPlayers.ScorePlayer1;
        Score2 = TwoPlayers.ScorePlayer2;

        ScorePlayer1 = findViewById(R.id.ScorePlayer1);
        ScorePlayer2 = findViewById(R.id.ScorePlayer2);


        ScorePlayer1.setText("Player1: "+ Score1);
        ScorePlayer2.setText("Player2: "+ Score2);

        /*TwoPlayers.ScorePlayer1 = 0;
        TwoPlayers.ScorePlayer2 = 0;
        */

    }
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_podstawowe,menu);
        return super.onCreateOptionsMenu(menu);


    }
}

