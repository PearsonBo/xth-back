package com.xth.model;

import lombok.Data;

import java.util.List;

/**
 * 接口权限信息
 *
 * @author
 * @date 2018/2/23
 */
@Data
public class PermissionInfo {

    /**
     * 统一权限集合
     */
    private List<CommonPermission> commonPermissionList;

    /**
     * 特殊权限集合
     */
    private List<SpecificPermission> specificPermissionList;

}
