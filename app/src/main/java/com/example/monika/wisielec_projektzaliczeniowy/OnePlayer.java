package com.example.monika.wisielec_projektzaliczeniowy;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class OnePlayer extends AppCompatActivity {
    int RandomWord;
    int IndBad;
    int Guesses;
    String input;
    String[] words;
    String slowa;
    Button ButtonCheckEnter;
    Button closePopupBtn;
    EditText OneLetter;
    RelativeLayout linear;
    LinearLayout LinearWithtTextView;
    TextView TextViewPopupWin;
    TextView[] textViews;
    Random r;
    ImageView image;
    Set<String> SetOfUsedCharsInWord;
    PopupWindow popupWindow;

    MyKeyboard keyboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jedengracz);

        final Intent intent = getIntent();
        slowa = intent.getStringExtra("text");
        linear = findViewById(R.id.relative);
        OneLetter = findViewById(R.id.EditText);
        ButtonCheckEnter = findViewById(R.id.button_enter);
        image = findViewById(R.id.imageView1);
        LinearWithtTextView = findViewById(R.id.LinearTextViews);
        image.setAlpha(127);
        r = new Random();
        //generuje sobie dowolny word z pliku
        RandomWord = r.nextInt(15);
        Guesses = 0;
        SetOfUsedCharsInWord = new HashSet<>();

        /////////////////////////////////////////////////////////
        //popup
        LayoutInflater layoutInflater = (LayoutInflater) OnePlayer.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customView = layoutInflater.inflate(R.layout.popup_window_lose,null);
        closePopupBtn = customView.findViewById(R.id.closePopupBtn);
        TextViewPopupWin = customView.findViewById(R.id.ulose);
        popupWindow = new PopupWindow(customView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        /////////////////////////////////////////////////////////

        keyboard = findViewById(R.id.keyboard);
        OneLetter.setRawInputType(InputType.TYPE_CLASS_TEXT);
        OneLetter.setTextIsSelectable(true);
        OneLetter.setClickable(false);

        InputConnection ic = OneLetter.onCreateInputConnection(new EditorInfo());
        keyboard.setInputConnection(ic);

        words = slowa.split(",\\s*");

        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].replaceAll("[^\\w]", "");
        }
        //tutaj sobie tworze dynamicznie textview, aby wpisac daną literkę pod niego
        textViews = new TextView[words[RandomWord].length()];
        for (int i = 0; i < words[RandomWord].length(); i++) {
            textViews[i] = new TextView(this);
            textViews[i].setTypeface(Typeface.MONOSPACE);
            textViews[i].setText("" + words[RandomWord].charAt(i));
            textViews[i].setTextColor(Color.TRANSPARENT);
            textViews[i].setBackgroundResource(R.drawable.rounded_corner);
            textViews[i].setTextSize(30);
            LinearWithtTextView.addView(textViews[i]);

            TextViewCompat.setAutoSizeTextTypeWithDefaults(
                    textViews[i],TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM
            );
        }

        ButtonCheckEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input = OneLetter.getText().toString();
                boolean guessed_letter = false;
                boolean already_guessed_letter = false;
                //jezeli nic nie ma to mi wyrzuci bląd w pobieraniu pierwszej literki
                if (input.isEmpty()) {
                }
                else {
                    if (SetOfUsedCharsInWord.contains(input)) {
                        Toast.makeText(OnePlayer.this,
                                "U ALREADY USED THIS LETTER!", Toast.LENGTH_LONG).show();
                        already_guessed_letter = true;
                    }
                    for (TextView textView : textViews) {
                        //sprawdzam czy w textview jest taki char i czy stringa z textview nie ma w secie
                        if ((textView.getText().toString()).equals(input) && !SetOfUsedCharsInWord.contains(input)) {
                            textView.setTextColor(Color.BLACK);
                            guessed_letter = true;
                        }
                    }
                    if(guessed_letter)
                    {
                        Guesses = Guesses + 1;
                    }
                    SetOfUsedCharsInWord.add(input);
                    OneLetter.getText().clear();
                    //wygrana!
                    if (Guesses == CountCharsInWordWithoutRepetition(words[RandomWord])) {
                        TextViewPopupWin.setText("YOU WIN");
                        popupWindow.showAtLocation(linear, Gravity.CENTER, 0, 0);
                        closePopupBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                            }
                        });
                    }
                    if (!guessed_letter  && !already_guessed_letter) {
                        ++IndBad;
                        //lose
                        if (IndBad == 9) {
                            TextViewPopupWin.setText("YOU LOSE. WORD: " + words[RandomWord]);
                            popupWindow.showAtLocation(linear, Gravity.CENTER, 0, 0);
                            closePopupBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = getIntent();
                                    finish();
                                    startActivity(intent);
                                }
                            });
                        }
                    }
                }
                ChangeHangmanImageview(IndBad);
            }
        });

    }
    int CountCharsInWordWithoutRepetition(String word) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (map.containsKey(c)) {
                int cnt = map.get(c);
                map.put(c, ++cnt);
            } else {
                map.put(c, 1);
            }
        }
        return map.size();
    }
    void ChangeHangmanImageview(int position)
    {
        if(position == 0)
            image.setImageResource(R.drawable.drawing_0);
        if(position == 1)
            image.setImageResource(R.drawable.drawing_1);
        if(position == 2)
            image.setImageResource(R.drawable.drawing_2);
        if(position == 3)
            image.setImageResource(R.drawable.drawing_3);
        if(position == 4)
            image.setImageResource(R.drawable.drawing_4);
        if(position == 5)
            image.setImageResource(R.drawable.drawing_5);
        if(position == 6)
            image.setImageResource(R.drawable.drawing_6);
        if(position == 7)
            image.setImageResource(R.drawable.drawing_7);
        if(position == 8)
            image.setImageResource(R.drawable.drawing_8);
        if(position == 9)
            image.setImageResource(R.drawable.drawing_9);
    }
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_podstawowe,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
