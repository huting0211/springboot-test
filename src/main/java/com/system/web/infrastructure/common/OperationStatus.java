package com.system.web.infrastructure.common;

public enum OperationStatus {
    Success("成功", 1),
    Failed("失败", 2),
    BusinessException("业务异常", 3),
    SystemException("系统异常", 4);

    private String display;

    private int status;

    OperationStatus(String display, int status) {
        this.display = display;
        this.status = status;
    }

    public String getDisplay() {
        return display;
    }

    public int getStatus() {
        return status;
    }
}
