package com.system.web.infrastructure.web.menu;


import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<MenuItem> menuItems;

    public Menu(String currentPath, MenuConfig menuConfig) {
        menuItems = new ArrayList<>();
        for (MenuItemConfig itemConfig : menuConfig.getMenuItems()) {
            MenuItem menuItem = constructMenuItem(currentPath, itemConfig);
            menuItems.add(menuItem);
        }
    }

    private MenuItem constructMenuItem(String currentPath, MenuItemConfig itemConfig) {
        if (null == itemConfig)
            return null;
        MenuItem menuItem = new MenuItem();
        menuItem.setIcon(itemConfig.getIcon());
        menuItem.setTitle(itemConfig.getTitle());
        menuItem.setUrl(itemConfig.getUrl());
        boolean isActive = getIsMatch(currentPath, itemConfig);

        if (itemConfig.getChildren() != null) {
            for (MenuItemConfig child : itemConfig.getChildren()) {
                MenuItem childItem = constructMenuItem(currentPath, child);
                if (childItem == null) {
                    continue;
                }
                isActive |= childItem.isActive();
                if (menuItem.getChildren() == null)
                    menuItem.setChildren(new ArrayList<>());
                menuItem.getChildren().add(childItem);
            }
        }

        menuItem.setActive(isActive);
        return menuItem;
    }

    private boolean getIsMatch(String currentPath, MenuItemConfig itemConfig) {
        List<String> activeMatches = itemConfig.getActiveMatches();
        if (activeMatches != null && activeMatches.size() > 0) {
            for (String activeMatch: activeMatches) {
                if (currentPath.equals(activeMatch))
                    return true;
            }
        }
        return currentPath.equals(itemConfig.getUrl());
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }
}
