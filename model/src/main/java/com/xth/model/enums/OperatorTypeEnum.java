package com.xth.model.enums;

/**
 * @Author: Hu Jianbo
 * @Date: 2018/9/10 0010 下午 23:46
 */
public enum OperatorTypeEnum {

    /**
     * 新建
     */
    CREATE("新建"),

    /**
     * 更新
     */
    UPDATE("更新"),

    /**
     * 删除
     */
    DELETE("删除");

    private String name;

    OperatorTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}