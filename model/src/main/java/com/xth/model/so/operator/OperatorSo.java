package com.xth.model.so.operator;


import com.xth.model.base.AbstractSo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 操作人
 *
 * @Author: Hu Jianbo
 * @Date: 2018/10/08 0010 下午 22:10
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OperatorSo extends AbstractSo {

    /**
     * 登录名
     */
    @ApiModelProperty("登录名")
    private String loginName;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String password;

    /**
     * 电话号码
     */
    @ApiModelProperty("电话号码")
    private String mobile;

    /**
     * 登录时间
     */
    @ApiModelProperty("登录时间")
    private Date loginTime;

    /**
     * 上次登录时间
     */
    @ApiModelProperty("上次登录时间")
    private Date lastLoginTime;

    /**
     * 登录错误次数
     */
    @ApiModelProperty("登录错误次数")
    private Integer passwordErrorTimes;

}
