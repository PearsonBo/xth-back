package com.xth.model.enums;

/**
 * @Author: Hu Jianbo
 * @Date: 2018/9/26 0026 下午 22:47
 */
public enum DiscountType {
    /**
     * 比率折扣
     */
    RATE("比率折扣"),

    /**
     * 满减
     */
    FULL_REDUCTION("满减");

    private String name;

    DiscountType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
