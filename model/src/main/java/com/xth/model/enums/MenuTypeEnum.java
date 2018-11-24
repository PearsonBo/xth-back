package com.xth.model.enums;

import java.util.Arrays;
import java.util.List;

/**
 * 菜单节点类型
 *
 * @author
 */
public enum MenuTypeEnum {

    /**
     * 普通菜单文件夹节点
     */
    MENU_DIR("普通菜单文件夹节点"),

    /**
     * 普通菜单节点
     */
    MENU("普通菜单节点"),

    /**
     * 权限节点
     */
    PERMISSION("权限节点");

    private String name;

    MenuTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static List<MenuTypeEnum> menuTreeTypes() {
        return Arrays.asList(MENU_DIR, MENU, PERMISSION);
    }
}
