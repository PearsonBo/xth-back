package com.xth.model.enums;

/**
 * @Author: Hu Jianbo
 * @Date: 2018/10/16 0016 下午 22:53
 */
public enum CityEnum {
    HZ("杭州"),
    BJ("北京"),
    SH("上海");

    CityEnum(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }
}
