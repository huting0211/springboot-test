package com.system.web.infrastructure.common;

public class SearchFilter {
    private String property;

    private SearchOperator operator;

    private Object value;

    private SearchConnector connector;

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public SearchOperator getOperator() {
        return operator;
    }

    public void setOperator(SearchOperator operator) {
        this.operator = operator;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public SearchConnector getConnector() {
        return connector;
    }

    public void setConnector(SearchConnector connector) {
        this.connector = connector;
    }
}
