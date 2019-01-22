package com.example.monika.wisielec_projektzaliczeniowy;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button Bjedengracz;
    Button Bdwochgraczy;
    Button Bwynik;
    Toolbar t;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bjedengracz = findViewById(R.id.button);
        Bdwochgraczy = findViewById(R.id.button2);
        Bwynik = findViewById(R.id.button3);


        Bjedengracz.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(),CategoryForOnePlayer.class);
                startActivity(i);
            }
        });
        Bdwochgraczy.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(),CategoryForTwoPlayers.class);
                startActivity(i);
            }
        });
        Bwynik.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(),ScoreClass.class);
                startActivity(i);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_podstawowe,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
