package com.system.web.infrastructure.common;

public enum SearchOperator {
    Equal(0, "等于", "="),
    NotEqual(1, "不等于", "!="),
    GreaterThan(2, "大于", ">"),
    GreaterThanEqual(3, "大于等于", ">="),
    LessThan(4, "小于", "<"),
    LessThanEqual(5, "小于等于", "<="),
    Like(6, "包含", "like");

    private int value;

    private String displayText;

    private String sqlOperator;

    SearchOperator(int value, String displayText, String sqlOperator) {
        this.value = value;
        this.displayText = displayText;
        this.sqlOperator = sqlOperator;
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

    public String getSqlOperator() {
        return sqlOperator;
    }

    public void setSqlOperator(String sqlOperator) {
        this.sqlOperator = sqlOperator;
    }
}
