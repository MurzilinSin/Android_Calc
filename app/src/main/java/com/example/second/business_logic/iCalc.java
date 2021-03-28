package com.example.second.business_logic;

import android.os.Parcel;
import android.os.Parcelable;

public class iCalc implements Parcelable {
    String value;
    double firstNumber, secondNumber, result;
    char action;
    boolean isFirstAction;
    boolean isDot;

    public iCalc(){}

    protected iCalc(Parcel in) {}

    @Override
    public void writeToParcel(Parcel dest, int flags) {}

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<iCalc> CREATOR = new Creator<iCalc>() {
        @Override
        public iCalc createFromParcel(Parcel in) {
            return new iCalc(in);
        }

        @Override
        public iCalc[] newArray(int size) {
            return new iCalc[size];
        }
    };

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
