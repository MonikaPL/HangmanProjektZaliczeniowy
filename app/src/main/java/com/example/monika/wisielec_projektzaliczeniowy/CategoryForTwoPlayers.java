package com.example.monika.wisielec_projektzaliczeniowy;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.io.InputStream;

public class CategoryForTwoPlayers extends Activity {
    Button b1;
    Button b2;
    Button b3;
    AssetManager mngr;
    InputStream is2;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kategoria);

        b1 = findViewById(R.id.button);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);


        mngr = getAssets();
        final String[] text = {""};

        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                try {

                    is2 = mngr.open("fruit.txt");
                    int size = is2.available();
                    byte[] buffer = new byte[size];
                    is2.read(buffer);
                    is2.close();
                    text[0] = new String(buffer);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(getApplicationContext(),TwoPlayers.class);

                intent.putExtra("text", text[0]);

                startActivity(intent);
            }

        });
        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                try {

                    is2 = mngr.open("animals.txt");
                    int size = is2.available();
                    byte[] buffer = new byte[size];
                    is2.read(buffer);
                    is2.close();
                    text[0] = new String(buffer);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(getApplicationContext(),TwoPlayers.class);

                intent.putExtra("text", text[0]);

                startActivity(intent);
            }

        });
        b3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                try {

                    is2 = mngr.open("vegetable.txt");
                    int size = is2.available();
                    byte[] buffer = new byte[size];
                    is2.read(buffer);
                    is2.close();
                    text[0] = new String(buffer);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(getApplicationContext(),TwoPlayers.class);

                intent.putExtra("text", text[0]);

                startActivity(intent);
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
