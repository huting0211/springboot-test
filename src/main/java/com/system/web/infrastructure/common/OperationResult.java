package com.system.web.infrastructure.common;

public class OperationResult<T> {

    private T object;

    private OperationStatus status;

    private String message;

    public OperationResult() {
        
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public OperationStatus getStatus() {
        return status;
    }

    public void setStatus(OperationStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}