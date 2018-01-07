package com.system.web.infrastructure.common;

public class OrderByParameter {
    private String property;

    private OrderByDirection direction;

    public OrderByDirection getDirection() {
        return direction;
    }

    public void setDirection(OrderByDirection direction) {
        this.direction = direction;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }
}
