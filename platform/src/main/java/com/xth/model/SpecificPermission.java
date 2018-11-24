package com.xth.model;

import lombok.Data;

/**
 * 特定的权限类
 *
 * @author
 * @date 2018/2/23
 */
@Data
public class SpecificPermission {

    /**
     * 接口地址
     */
    private String url;

    /**
     * 可访问权限
     */
    private String[] perms;

    /**
     * 可访问角色
     */
    private String[] roles;

    /**
     * 所有url
     */
    private String[] prefixes;

}
