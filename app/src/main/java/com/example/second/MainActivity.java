package com.example.second;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.example.second.business_logic.iCalc;

public class MainActivity extends AppCompatActivity {
    private Button one, two, three, four, five, six, seven, eight, nine, zero;
    private TextView display;
    com.example.second.business_logic.iCalc iCalc = new iCalc();
    String iCalcKey = "iCalcKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    private void btnNumbOnClick(Button button) {
        button.setOnClickListener(v -> {
            if (display.getText().toString().equals(iCalc.getValue())) { //!!!!!!!!!!!!!!!!!!!!
                display.setText("");
            }
            String text = button.getText().toString();
            this.display.setText(this.display.getText() + text);
            System.out.println(text);
        });
    }

    public void btnDoubleOnClick(View view){
        if(!iCalc.isDot()){
            iCalc.setValue(display.getText().toString()+".");
            display.setText(iCalc.getValue());
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
            double a = Double.parseDouble(display.getText().toString());
            if (a > 0) {
                a = -a;
            } else {
                a = Math.abs(a);
            }
            String text = String.valueOf(a);
            this.display.setText(text);
        }
    }

    public void btnDivideOnClick(View view) {
        if(!iCalc.isFirstAction()) {
            if (display.getText().toString().length() != 0) {
                iCalc.setAction('/');
                iCalc.setValue(display.getText().toString());
                display.setText("");

                iCalc.setFirstAction(true);
            }
        } else {
            if (display.getText().toString().length() != 0) {
                if(iCalc.getSecondNumber() != 0){
                    iCalc.setFirstNumber();
                    iCalc.setSecondNumber(Double.parseDouble(this.display.getText().toString()));
                    iCalc.setResult(iCalc.getFirstNumber()/iCalc.getSecondNumber());
                    iCalc.setValue("" + iCalc.getResult());
                    this.display.setText(iCalc.getValue());
                }
            }
        }
    }

    public void btnMultiplyOnClick(View view) {
        if(!iCalc.isFirstAction()) {
            if (display.getText().toString().length() != 0) {
                iCalc.setAction('*');
                iCalc.setValue(display.getText().toString());
                display.setText("");
                iCalc.setFirstAction(true);
            }
        } else {
            if (display.getText().toString().length() != 0) {
                iCalc.setFirstNumber();
                iCalc.setSecondNumber(Double.parseDouble(this.display.getText().toString()));
                iCalc.setResult(iCalc.getFirstNumber()*iCalc.getSecondNumber());
                iCalc.setValue("" + iCalc.getResult());
                this.display.setText(iCalc.getValue());
            }
        }
    }

    public void btnMinusOnClick(View view) {
        if(!iCalc.isFirstAction()) {
            if (display.getText().toString().length() != 0) {
                iCalc.setAction('-');
                iCalc.setValue(display.getText().toString());
                display.setText("");
                iCalc.setFirstAction(true);
            }
        } else {
            if (display.getText().toString().length() != 0) {
                iCalc.setFirstNumber();
                iCalc.setSecondNumber(Double.parseDouble(this.display.getText().toString()));
                iCalc.setResult(iCalc.getFirstNumber()-iCalc.getSecondNumber());
                iCalc.setValue("" + iCalc.getResult());
                this.display.setText(iCalc.getValue());
            }
        }
    }

    public void btnPlusOnClick(View view) {
        if (!iCalc.isFirstAction()) {
            if (display.getText().toString().length() != 0) {
                iCalc.setAction('+');
                iCalc.setValue(display.getText().toString());
                display.setText("");
                iCalc.setFirstAction(true);
            }
        } else {
            if (display.getText().toString().length() != 0) {
                iCalc.setFirstNumber();
                iCalc.setSecondNumber(Double.parseDouble(this.display.getText().toString()));
                iCalc.setResult(iCalc.getFirstNumber()+iCalc.getSecondNumber());
                iCalc.setValue("" + iCalc.getResult());
                this.display.setText(iCalc.getValue());
            }
        }
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
                    iCalc.setFirstNumber();
                    iCalc.setSecondNumber(Double.parseDouble(this.display.getText().toString()));
                    iCalc.setResult(iCalc.getFirstNumber()-iCalc.getSecondNumber());
                    iCalc.setValue("" + iCalc.getResult());
                    this.display.setText(iCalc.getValue());
                    break;
                case '+':
                    iCalc.setFirstNumber();
                    iCalc.setSecondNumber(Double.parseDouble(this.display.getText().toString()));
                    iCalc.setResult(iCalc.getFirstNumber()+iCalc.getSecondNumber());
                    iCalc.setValue("" + iCalc.getResult());
                    this.display.setText(iCalc.getValue());
                    break;
                case '*':
                    iCalc.setFirstNumber();
                    iCalc.setSecondNumber(Double.parseDouble(this.display.getText().toString()));
                    iCalc.setResult(iCalc.getFirstNumber()*iCalc.getSecondNumber());
                    iCalc.setValue("" + iCalc.getResult());
                    this.display.setText(iCalc.getValue());
                    break;
                case '/':
                    iCalc.setFirstNumber();
                    iCalc.setSecondNumber(Double.parseDouble(this.display.getText().toString()));
                    if(iCalc.getSecondNumber() != 0){
                        iCalc.setResult(iCalc.getFirstNumber()/iCalc.getSecondNumber());
                        iCalc.setValue("" + iCalc.getResult());
                        this.display.setText(iCalc.getValue());
                    } else {
                        btnResetOnClick(view);
                    }
                    break;
                default:
                    break;
            }
            iCalc.setFirstAction(false);
            iCalc.setDot(true);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(iCalcKey,iCalc);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        iCalc = (iCalc)savedInstanceState.getParcelable(iCalcKey);
    }
}