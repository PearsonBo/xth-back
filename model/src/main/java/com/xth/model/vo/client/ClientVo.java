package com.xth.model.vo.client;

import com.xth.model.base.AbstractVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @Author: Hu Jianbo
 * @Date: 2018/9/18 0010 下午 22:12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "客户")
public class ClientVo extends AbstractVo {

    /**
     * 昵称
     */
    @ApiModelProperty("昵称")
    private String nickname;

    /**
     * 真实姓名
     */
    @ApiModelProperty("真实姓名")
    private String username;

    /**
     * 性别
     */
    @ApiModelProperty("性别")
    private String gender;

    /**
     * 身份证号
     */
    @ApiModelProperty("身份证号")
    private String idNumber;

    /**
     * 已领优惠券的ID集合,已|分割
     */
    @ApiModelProperty("已领优惠券的ID集合,已|分割")
    private String couponIds;

    /**
     * 客户会员等级id
     */
    @ApiModelProperty("客户会员等级id")
    private Long clientLevelId;

    /**
     * 累积登录次数
     */
    @ApiModelProperty("累积登录次数")
    private Long loginNum;

    /**
     * 最后一次登录时间
     */
    @ApiModelProperty("最后一次登录时间")
    private Date lastLoginTime;

    /**
     * 用户唯一标志
     */
    @ApiModelProperty("用户唯一标志")
    private String openId;

    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    private String mobile;

    /**
     * 最近登录经度
     */
    @ApiModelProperty("最近登录经度")
    private String lastLongitude;

    /**
     * 最近登录纬度
     */
    @ApiModelProperty("最近登录纬度")
    private String lastLatitude;

}
