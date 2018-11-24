package com.xth.model.vo.menu;

import com.xth.model.base.AbstractVo;
import com.xth.model.enums.CommonActiveStatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Created by 系统角色
 *
 * @author bl00048
 * @date on 2017/12/28.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleVo extends AbstractVo {

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;

    /**
     * 类型
     */
    @ApiModelProperty("类型")
    private String roleType;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;

    /**
     * 是否锁定
     */
    @ApiModelProperty("是否锁定")
    private CommonActiveStatusEnum status;

    /**
     * 角色关联菜单Id集合
     */
    @ApiModelProperty("角色关联菜单Id集合")
    private List<Long> menuItemIdList;
}
