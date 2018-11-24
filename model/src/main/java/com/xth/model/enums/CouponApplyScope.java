package com.xth.model.enums;

/**
 * @Author: Hu Jianbo
 * @Date: 2018/9/25 0025 下午 21:37
 */
public enum CouponApplyScope {
    /**
     * 省
     */
    PROVINCE("省"),
    /**
     * 市
     */
    CITY("市"),
    /**
     * 商户
     */
    COMPANY("商户"),
    /**
     * 场馆
     */
    STORE("场馆");


    private String name;

    CouponApplyScope(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
