package com.system.web.infrastructure.common;

public class FormModel<T> {
    private FormType formType;

    private T formModel;

    private String message;

    public T getFormModel() {
        return formModel;
    }

    public void setFormModel(T formModel) {
        this.formModel = formModel;
    }

    public FormType getFormType() {
        return formType;
    }

    public void setFormType(FormType formType) {
        this.formType = formType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
