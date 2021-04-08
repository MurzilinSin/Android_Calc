package com.example.second.business_logic;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class iCalc implements Serializable {
    String value;
    double firstNumber, secondNumber, result;
    char action;
    boolean isFirstAction;
    boolean isDot;

    public iCalc(){}

    protected iCalc(Parcel in) {}

    public char getAction() {
        return action;
    }

    public void setAction(char action) {
        this.action = action;
    }

    public boolean isDot() {
        return isDot;
    }

    public void setDot(boolean dot) {
        isDot = dot;
    }

    public boolean isFirstAction() {
        return isFirstAction;
    }

    public void setFirstAction(boolean firstAction) {
        isFirstAction = firstAction;
    }

    public double getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber() {
        this.firstNumber = Double.parseDouble(this.getValue());
    }

    public double getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(double secondNumber) {
        this.secondNumber = secondNumber;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String firstValue) {
        this.value = firstValue;
    }

}
