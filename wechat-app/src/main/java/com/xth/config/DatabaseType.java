package com.xth.config;

/**
 * @Author: Hu Jianbo
 * @Date: 2018/9/11 0011 下午 22:49
 */
public enum DatabaseType {
    /**
     * BP_DS
     */
    BP_DS("bestPayDataSource"),
    /**
     * WYVERN_DATA_SOURCE
     */
    WYVERN_DS("WYVERN_DATA_SOURCE");

    private String name;

    DatabaseType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
