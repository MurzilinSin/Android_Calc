package com.example.second;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.example.second.business_logic.iCalc;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private Button one, two, three, four, five, six, seven, eight, nine, zero;
    private TextView display;
    com.example.second.business_logic.iCalc iCalc = new iCalc();
    String iCalcKey = "iCalcKey";
    SharedPreferences sharedPreferences;
    private static final String FILENAME = "myTheme";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences = getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        dayNight();
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_main);
        initViews();
        btnNumbOnClick(one);
        btnNumbOnClick(two);
        btnNumbOnClick(three);
        btnNumbOnClick(four);
        btnNumbOnClick(five);
        btnNumbOnClick(six);
        btnNumbOnClick(seven);
        btnNumbOnClick(eight);
        btnNumbOnClick(nine);
        btnNumbOnClick(zero);
    }

    private void dayNight(){
        if(sharedPreferences.contains("themeName")) {
            setTheme(Integer.parseInt(sharedPreferences.getString("themeName","")));
        }
        else {
            if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
                setTheme(R.style.darkTheme);
            }
            else {
                setTheme(R.style.AppTheme);
            }
        }
    }

    private void initViews() {
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        zero = findViewById(R.id.zero);
        display = findViewById(R.id.display);
    }

    public void btnSettingsOnClick(View view){
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intent);
    }

    private void btnNumbOnClick(Button button) {
        button.setOnClickListener(v -> {
            if (display.getText().toString().equals(iCalc.getValue())) {
                display.setText("");
            }
            String text = button.getText().toString();
            this.display.setText(this.display.getText() + text);
        });
    }

    public void btnDoubleOnClick(View view){
        if(!iCalc.isDot()){
            iCalc.setValue(display.getText().toString());
            display.setText(iCalc.getValue() + ".");
            iCalc.setDot(true);
        }
    }

    public void btnResetOnClick(View view) {
        iCalc.setValue("");
        iCalc.setAction('a');
        iCalc.setDot(false);
        this.display.setText(iCalc.getValue());
        iCalc.setFirstAction(false);
    }

    public void btnPercentOnClick(View view) {
        if (display.getText().toString().length() != 0) {
            double a = Double.parseDouble(display.getText().toString());
            String text = "" + a / 100;
            display.setText(text);
        }
    }

    public void btnModuleOnClick(View view) {
        if (display.getText().toString().length() != 0) {
            double num = Double.parseDouble(display.getText().toString());
            if (num > 0) {
                num = -num;
            } else {
                num = Math.abs(num);
            }
            String text = String.valueOf(num);
            setTextWithoutDotOrWithDot(text);
        }
    }

    private void setTextWithoutDotOrWithDot(String text) {
        char[] words = text.toCharArray();
        int index = 0;
        for (int i = 0; i < words.length; i++) {
            if(words[i] == '.'){
               index = i;
            }
        }
        String numbs = text.substring(index+1);
        if(Long.parseLong(numbs) == 0){
            text = text.substring(0,index);
        }
        this.display.setText(text);
    }

    public void btnDivideOnClick(View view) {
        if(!iCalc.isFirstAction()) {
            if (display.getText().toString().length() != 0) {
                iCalc.setAction('/');
                setValueAndFirstNumber();
            }
        } else {
            if (display.getText().toString().length() != 0) {
                if(iCalc.getSecondNumber() != 0){
                    iCalc.setFirstNumber();
                    iCalc.setSecondNumber(Double.parseDouble(this.display.getText().toString()));
                    setResultSecondAction(iCalc.getFirstNumber() / iCalc.getSecondNumber());
                }
            }
        }
        iCalc.setDot(false);
    }

    public void btnMultiplyOnClick(View view) {
        if(!iCalc.isFirstAction()) {
            if (display.getText().toString().length() != 0) {
                iCalc.setAction('*');
                setValueAndFirstNumber();
            }
        } else {
            if (display.getText().toString().length() != 0) {
                iCalc.setFirstNumber();
                iCalc.setSecondNumber(Double.parseDouble(this.display.getText().toString()));
                setResultSecondAction(iCalc.getFirstNumber() * iCalc.getSecondNumber());
            }
        }
        iCalc.setDot(false);
    }

    public void btnMinusOnClick(View view) {
        if(!iCalc.isFirstAction()) {
            if (display.getText().toString().length() != 0) {
                iCalc.setAction('-');
                setValueAndFirstNumber();
            }
        } else {
            if (display.getText().toString().length() != 0) {
                iCalc.setFirstNumber();
                iCalc.setSecondNumber(Double.parseDouble(this.display.getText().toString()));
                setResultSecondAction(iCalc.getFirstNumber() - iCalc.getSecondNumber());
            }
        }
        iCalc.setDot(false);
    }

    public void btnPlusOnClick(View view) {
        if (!iCalc.isFirstAction()) {
            if (display.getText().toString().length() != 0) {
                iCalc.setAction('+');
                setValueAndFirstNumber();
            }
        } else {
            if (display.getText().toString().length() != 0) {
                iCalc.setFirstNumber();
                iCalc.setSecondNumber(Double.parseDouble(this.display.getText().toString()));
                setResultSecondAction(iCalc.getFirstNumber() + iCalc.getSecondNumber());
            }
        }
        iCalc.setDot(false);
    }

    public void btnEqualOnClick(View view) {
        if (display.getText().equals("")) {
            return;
        } else if ((iCalc.getAction() == '+' || iCalc.getAction() == '-' || iCalc.getAction() == '*' || iCalc.getAction() == '/') && iCalc.getValue() == null) {
            return;
        } else {
            String text = "";
            switch (iCalc.getAction()) {
                case '-':
                    iCalc.setSecondNumber(Double.parseDouble(this.display.getText().toString()));
                    setResult(iCalc.getFirstNumber()-iCalc.getSecondNumber());
                    break;
                case '+':
                    iCalc.setSecondNumber(Double.parseDouble(this.display.getText().toString()));
                    setResult(iCalc.getFirstNumber()+iCalc.getSecondNumber());
                    break;
                case '*':
                    iCalc.setSecondNumber(Double.parseDouble(this.display.getText().toString()));
                    setResult(iCalc.getFirstNumber()*iCalc.getSecondNumber());
                    break;
                case '/':
                    iCalc.setFirstNumber();
                    iCalc.setSecondNumber(Double.parseDouble(this.display.getText().toString()));
                    if(iCalc.getSecondNumber() != 0){
                        iCalc.setResult(iCalc.getFirstNumber()/iCalc.getSecondNumber());
                        iCalc.setValue("" + iCalc.getResult());
                        setTextWithoutDotOrWithDot(iCalc.getValue());
                    } else {
                        btnResetOnClick(view);
                    }
                    break;
                default:
                    break;
            }
            iCalc.setFirstAction(false);
            iCalc.setDot(false);
        }
    }

    private void setValueAndFirstNumber() {
        iCalc.setValue(display.getText().toString());
        display.setText("");
        iCalc.setFirstAction(true);
        iCalc.setFirstNumber();
    }

    private void setResult(Double calcResult){
        iCalc.setResult(calcResult);
        iCalc.setValue(String.valueOf(iCalc.getResult()));
        this.display.setText(iCalc.getValue());
    }

    private void setResultSecondAction(double calcResult) {
        iCalc.setResult(calcResult);
        iCalc.setValue(String.valueOf(iCalc.getResult()));
        this.display.setText(iCalc.getValue());
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(iCalcKey,iCalc);
    }


    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        iCalc = (iCalc)savedInstanceState.getSerializable(iCalcKey);
        display.setText(iCalc.getValue());
    }
}