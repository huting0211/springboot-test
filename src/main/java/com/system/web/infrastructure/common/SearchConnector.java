package com.system.web.infrastructure.common;

public enum SearchConnector {
    AND(0, "并且"),
    OR(1, "或者");


    private int value;
    private String displayText;

    SearchConnector(int value, String displayText){
        this.value = value;
        this.displayText = displayText;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDisplayText() {
        return displayText;
    }

    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }
}
