package org.sber;


public class Child extends Parent {
    public static final String CHILD_NAME = "CHILD_NAME";
    public static final String CHILD_VALUE = "CHILD_VALUE";
    private int childField;

    public Child(int childField) {
        this.childField = childField;
    }

    public void childPublicMethod() {
    }

    private void childPrivateMethod() {
    }

    public int getChildField() {
        return childField;
    }

    public void setChildField(int childField) {
        this.childField = childField;
    }
}
