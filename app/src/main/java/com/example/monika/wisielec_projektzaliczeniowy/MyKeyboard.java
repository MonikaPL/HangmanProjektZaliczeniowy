package com.example.monika.wisielec_projektzaliczeniowy;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.LinearLayout;

public class MyKeyboard extends LinearLayout implements View.OnClickListener {

    private Button button1, button2, button3, button4,
            button5, button6, button7, button8,
            button9, button10,button11, button12,button13, button14,button15, button16,button17, button18,button19, button20,
            button21, button22,button23, button24,button25, button26, buttonDelete, buttonEnter;

    private SparseArray<String> keyValues = new SparseArray<>();
    private InputConnection inputConnection;

    public MyKeyboard(Context context) {
        this(context, null, 0);
    }

    public MyKeyboard(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyKeyboard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.keyboard, this, true);
        button1 = findViewById(R.id.button_a);
        button1.setOnClickListener(this);
        button2 = findViewById(R.id.button_b);
        button2.setOnClickListener(this);
        button3 = findViewById(R.id.button_c);
        button3.setOnClickListener(this);
        button4 = findViewById(R.id.button_d);
        button4.setOnClickListener(this);
        button5 = findViewById(R.id.button_e);
        button5.setOnClickListener(this);
        button6 = findViewById(R.id.button_f);
        button6.setOnClickListener(this);
        button7 = findViewById(R.id.button_g);
        button7.setOnClickListener(this);
        button8 = findViewById(R.id.button_h);
        button8.setOnClickListener(this);
        button9 = findViewById(R.id.button_i);
        button9.setOnClickListener(this);
        button10 = findViewById(R.id.button_j);
        button10.setOnClickListener(this);
        button11 = findViewById(R.id.button_k);
        button11.setOnClickListener(this);
        button12 = findViewById(R.id.button_l);
        button12.setOnClickListener(this);
        button13 = findViewById(R.id.button_m);
        button13.setOnClickListener(this);
        button14 = findViewById(R.id.button_n);
        button14.setOnClickListener(this);
        button15 = findViewById(R.id.button_o);
        button15.setOnClickListener(this);
        button16 = findViewById(R.id.button_p);
        button16.setOnClickListener(this);
        button17 = findViewById(R.id.button_q);
        button17.setOnClickListener(this);
        button18 = findViewById(R.id.button_r);
        button18.setOnClickListener(this);
        button19 = findViewById(R.id.button_s);
        button19.setOnClickListener(this);
        button20 = findViewById(R.id.button_t);
        button20.setOnClickListener(this);
        button21 = findViewById(R.id.button_u);
        button21.setOnClickListener(this);
        button22 = findViewById(R.id.button_v);
        button22.setOnClickListener(this);
        button23 = findViewById(R.id.button_w);
        button23.setOnClickListener(this);
        button24 = findViewById(R.id.button_x);
        button24.setOnClickListener(this);
        button25 = findViewById(R.id.button_y);
        button25.setOnClickListener(this);
        button26 = findViewById(R.id.button_z);
        button26.setOnClickListener(this);

        buttonDelete = findViewById(R.id.button_delete);
        buttonDelete.setOnClickListener(this);
        buttonEnter = findViewById(R.id.button_enter);
        buttonEnter.setOnClickListener(this);

        keyValues.put(R.id.button_a, "a");
        keyValues.put(R.id.button_b, "b");
        keyValues.put(R.id.button_c, "c");
        keyValues.put(R.id.button_d, "d");
        keyValues.put(R.id.button_e, "e");
        keyValues.put(R.id.button_f, "f");
        keyValues.put(R.id.button_g, "g");
        keyValues.put(R.id.button_h, "h");
        keyValues.put(R.id.button_i, "i");
        keyValues.put(R.id.button_j, "j");
        keyValues.put(R.id.button_k, "k");
        keyValues.put(R.id.button_l, "l");
        keyValues.put(R.id.button_m, "m");
        keyValues.put(R.id.button_n, "n");
        keyValues.put(R.id.button_o, "o");
        keyValues.put(R.id.button_p, "p");
        keyValues.put(R.id.button_q, "q");
        keyValues.put(R.id.button_r, "r");
        keyValues.put(R.id.button_s, "s");
        keyValues.put(R.id.button_t, "t");
        keyValues.put(R.id.button_u, "u");
        keyValues.put(R.id.button_v, "v");
        keyValues.put(R.id.button_w, "w");
        keyValues.put(R.id.button_x, "x");
        keyValues.put(R.id.button_y, "y");
        keyValues.put(R.id.button_z, "z");

        keyValues.put(R.id.button_enter, "\n");
    }

    @Override
    public void onClick(View view) {
        //((Button)view).setTextColor(getResources().getColor(R.color.colorRed));
        if (inputConnection == null)
            return;

        if (view.getId() == R.id.button_delete) {
            CharSequence selectedText = inputConnection.getSelectedText(0);

            if (TextUtils.isEmpty(selectedText)) {
                inputConnection.deleteSurroundingText(1, 0);
            } else {
                inputConnection.commitText("", 1);
            }
        } else {
            String value = keyValues.get(view.getId());
            inputConnection.commitText(value, 1);

        }
    }

    public void setInputConnection(InputConnection ic) {
        inputConnection = ic;
    }
}