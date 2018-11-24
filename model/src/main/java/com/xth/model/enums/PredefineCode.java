package com.xth.model.enums;

/**
 * @Author: Hu Jianbo
 * @Date: 2018/9/28 0028 下午 23:22
 */
public enum PredefineCode {

    /**
     * 未登录
     */
    NOT_LOGIN("9999");

    private String name;

    PredefineCode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
