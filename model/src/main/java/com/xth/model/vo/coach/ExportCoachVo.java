package com.xth.model.vo.coach;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Hu Jianbo
 * @Date: 2018/9/17 0010 下午 22:12
 */
@Data
public class ExportCoachVo implements Serializable {

    /**
     * 教练姓名
     */
    @ApiModelProperty("教练姓名")
    private String name;

    /**
     * 性别
     */
    @ApiModelProperty("性别")
    private String gender;

    /**
     * 出生年份（到年）
     */
    @ApiModelProperty("出生年份（到年）")
    private Integer birthYear;

    /**
     * 身份证号
     */
    @ApiModelProperty("身份证号")
    private String idNumber;

    /**
     * 联系电话
     */
    @ApiModelProperty("联系电话")
    private String phone;

    /**
     * 所属场馆id
     */
    @ApiModelProperty("所属场馆id")
    private Long storeId;

    /**
     * 特长描述
     */
    @ApiModelProperty("特长描述")
    private String speciality;

    /**
     * 星级
     */
    @ApiModelProperty("星级")
    private Integer starLevel;

    /**
     * 是否是金牌教练
     */
    @ApiModelProperty("是否是金牌教练,true代表是")
    private Boolean isGold;

}
