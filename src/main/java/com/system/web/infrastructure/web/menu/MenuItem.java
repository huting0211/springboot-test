package com.system.web.infrastructure.web.menu;

public class MenuItem extends MenuItemConfig {
    private boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
