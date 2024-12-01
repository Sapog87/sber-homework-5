package org.sber;

public class ToClass {
    // public для удобства демонстрация
    private int intField;
    private String stringField;
    private double justField;

    public ToClass(int intField, String stringField, double justField) {
        this.intField = intField;
        this.stringField = stringField;
        this.justField = justField;
    }

    public void setIntField(int intField) {
        this.intField = intField;
    }

    public void setStringField(String stringField) {
        this.stringField = stringField;
    }

    public void setJustField(double justField) {
        this.justField = justField;
    }

    public int getIntField() {
        return intField;
    }

    public String getStringField() {
        return stringField;
    }

    public double getJustField() {
        return justField;
    }
}
