package com.system.web.infrastructure.web.menu;


import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "com-system-web.menu.menuItems")
public class MenuItemConfig {

    private String title;
    private String icon;
    private String url;
    private List<String> activeMatches;

    private List<MenuItemConfig> children;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<MenuItemConfig> getChildren() {
        return children;
    }

    public void setChildren(List<MenuItemConfig> children) {
        this.children = children;
    }

    public List<String> getActiveMatches() {
        return activeMatches;
    }

    public void setActiveMatches(List<String> activeMatches) {
        this.activeMatches = activeMatches;
    }
}