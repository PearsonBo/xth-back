package com.xth.model.vo.menu;

import com.xth.model.enums.MenuTypeEnum;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 菜单信息
 *
 * @author
 */
@Data
public class MenuItemVo {

    private static final long serialVersionUID = 6440943617383720938L;

    private Long id;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;

    /**
     * 创建人id
     */
    private Long creatorId;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 更新人id
     */
    private Long updaterId;

    /**
     * 更新人
     */
    private String updater;

    /**
     * 乐观锁
     */
    private Integer lockVersion;

    /**
     * domain
     */
    private Long domainId;

    /**
     * 返回值1
     */
    private String udf1;

    /**
     * 返回值2
     */
    private String udf2;

    /**
     * 返回值3
     */
    private String udf3;

    /**
     * 返回值4
     */
    private String udf4;

    /**
     * 返回值5
     */
    private String udf5;

    /**
     * 返回值6
     */
    private String udf6;

    /**
     * 权限名
     */
    private String name;

    /**
     * 权限类型
     */
    private MenuTypeEnum type;

    /**
     * 菜单url
     */
    private String url;

    /**
     * 父菜单
     */
    private Long parentId;

    /**
     * 父菜单路径
     */
    private String parentPath;

    /**
     * 权限控制
     */
    private String permission;

    /**
     * 排序
     */
    private Long sortIndex;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否锁定
     */
    private Boolean locked = Boolean.FALSE;

    /**
     * js真实路径，优先取该值
     */
    private String jsUrl;

    /**
     * 权限集合
     */
    private List<String> permissionList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MenuTypeEnum getType() {
        return type;
    }

    public void setType(MenuTypeEnum type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentPath() {
        return parentPath;
    }

    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Long getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Long sortIndex) {
        this.sortIndex = sortIndex;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public String getJsUrl() {
        return jsUrl;
    }

    public void setJsUrl(String jsUrl) {
        this.jsUrl = jsUrl;
    }

}
