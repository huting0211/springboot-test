package com.system.web.infrastructure.web.menu;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "com-system-web.menu")
public class MenuConfig {
    private List<MenuItemConfig> menuItems;

    public List<MenuItemConfig> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItemConfig> menuItems) {
        this.menuItems = menuItems;
    }
}
