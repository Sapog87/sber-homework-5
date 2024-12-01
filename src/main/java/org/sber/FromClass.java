package org.sber;

public class FromClass {
    // public для удобства демонстрация
    private int intField;
    private String stringField;
    private double doubleField;

    public FromClass(int intField, String stringField, double doubleField) {
        this.intField = intField;
        this.stringField = stringField;
        this.doubleField = doubleField;
    }

    public int getIntField() {
        return intField;
    }

    public String getStringField() {
        return stringField;
    }

    public double getDoubleField() {
        return doubleField;
    }
}
