package com.xth.model.enums;

/**
 * @Author: Hu Jianbo
 * @Date: 2018/9/24 0024 下午 21:00
 */
public enum LevelType {

    /**
     * 商户
     */
    COMPANY("商户"),
    /**
     * 场馆
     */
    STORE("场馆"),
    /**
     * 教练
     */
    COACH("教练"),
    /**
     * 客户
     */
    CLIENT("客户");


    private String name;

    LevelType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
