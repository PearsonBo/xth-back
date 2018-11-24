package com.xth.model.enums;

/**
 * @Author: Hu Jianbo
 * @Date: 2018/9/26 0026 下午 22:47
 */
public enum RebateStatusEnum {
    /**
     * 已提交
     */
    SUBMITED("已提交"),

    /**
     * 废弃
     */
    INACTIVE("废弃"),

    /**
     * 已领
     */
    DONE("已领");

    private String name;

    RebateStatusEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
