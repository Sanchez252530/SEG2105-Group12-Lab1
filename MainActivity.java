package com.example.simplecalculator;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import java.math.BigDecimal;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity implements
        GestureDetector.OnGestureListener,
        View.OnClickListener{

    Button buttonAdd, buttonMult, buttonSub, buttonDiv, buttonDec, buttonClr, buttonCompute, button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    TextView text_display;
    ScriptEngine engine;
    private static final String DEBUG_TAG = "Gestures";
    private GestureDetectorCompat mDetector;

// Called when the activity is first created.
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Instantiate the gesture detector with the
        // application context and an implementation of
        // GestureDetector.OnGestureListener.
        mDetector = new GestureDetectorCompat(this, this);
        // Set the gesture detector as the double-tap
        // listener.
    }
    public void enterApp(){
        setContentView(R.layout.activity_2);
        engine = new ScriptEngineManager().getEngineByName("rhino");

        text_display=(TextView) findViewById(R.id.outputText);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonSub = (Button) findViewById(R.id.buttonSub);
        buttonDiv = (Button) findViewById(R.id.buttonDiv);
        buttonDec = (Button) findViewById(R.id.buttonDec);
        buttonMult = (Button) findViewById(R.id.buttonMult);
        buttonClr = (Button) findViewById(R.id.buttonClr);
        buttonCompute = (Button) findViewById(R.id.buttonCompute);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);

        setClickListeners();
    }

    public void setClickListeners(){
        buttonAdd.setOnClickListener(this);
        buttonSub.setOnClickListener(this);
        buttonDec.setOnClickListener(this);
        buttonDiv.setOnClickListener(this);
        buttonMult.setOnClickListener(this);
        buttonClr.setOnClickListener(this);
        buttonCompute.setOnClickListener(this);
        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);

    }

// Looking for any touch inputs to go from intro screen to actual calculator
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.mDetector.onTouchEvent(event)) {
            enterApp();
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent event) {
        Log.d(DEBUG_TAG, "onDown: " + event.toString());
        return true;
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2,
                           float velocityX, float velocityY) {
        Log.d(DEBUG_TAG, "onFling: " + event1.toString() + event2.toString());
        return true;
    }

    @Override
    public void onLongPress(MotionEvent event) {
        Log.d(DEBUG_TAG, "onLongPress: " + event.toString());
    }

    @Override
    public boolean onScroll(MotionEvent event1, MotionEvent event2, float distanceX,
                            float distanceY) {
        Log.d(DEBUG_TAG, "onScroll: " + event1.toString() + event2.toString());
        return true;
    }

    @Override
    public void onShowPress(MotionEvent event) {
        Log.d(DEBUG_TAG, "onShowPress: " + event.toString());
    }

    @Override
    public boolean onSingleTapUp(MotionEvent event) {
        Log.d(DEBUG_TAG, "onSingleTapUp: " + event.toString());
        return true;
    }

// Finished defining all input detection methods
// Defining listening function

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonAdd:
                addNumber("+");
                break;
            case R.id.buttonSub:
                addNumber("-");
                break;
            case R.id.buttonDiv:
                addNumber("/");
                break;
            case R.id.buttonMult:
                addNumber("*");
                break;
            case R.id.buttonDec:
                addNumber(".");
                break;
            case R.id.buttonClr:
                clearDisplay();
                break;
            case R.id.buttonCompute:
                String result = null;
                try {
                    result = compute(text_display.getText().toString());
                } catch (ScriptException e) {
                    throw new RuntimeException(e);
                }
                text_display.setText(result);
                break;
            case R.id.button0:
                addNumber("0");
                break;
            case R.id.button1:
                addNumber("1");
                break;
            case R.id.button2:
                addNumber("2");
                break;
            case R.id.button3:
                addNumber("3");
                break;
            case R.id.button4:
                addNumber("4");
                break;
            case R.id.button5:
                addNumber("5");
                break;
            case R.id.button6:
                addNumber("6");
                break;
            case R.id.button7:
                addNumber("7");
                break;
            case R.id.button8:
                addNumber("8");
                break;
            case R.id.button9:
                addNumber("9");
                break;
        }

    }


    private void addNumber(String number){
        text_display.setText(text_display.getText()+number);
    }
    private void clearDisplay(){
        text_display.setText("");
    }
    private String compute(String expression) throws ScriptException {
        String result = engine.eval(expression).toString();
        BigDecimal decimal = new BigDecimal(result);
        return decimal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();

    }

// Methods to do arithmetic

}