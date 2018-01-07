package com.system.web.infrastructure.common;

import java.util.HashMap;
import java.util.Map;

public enum OrderByDirection {
    Ascending(0, "asc", "升序"),
    Descending(1, "desc", "降序");


    private int value;

    private String shortName;

    private String displayText;

    OrderByDirection(int value, String shortName, String displayText){
        this.value = value;
        this.shortName = shortName;
        this.displayText = displayText;
    }

    public String getDisplayText() {
        return displayText;
    }

    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}
