package com.xth.model.vo.coach;

import com.xth.model.base.AbstractVo;
import com.xth.model.vo.store.StoreVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: Hu Jianbo
 * @Date: 2018/9/17 0010 下午 22:12
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CoachVo extends AbstractVo {

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
    private String birthYear;

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
    private String storeId;

    /**
     * 特长描述
     */
    @ApiModelProperty("特长描述")
    private String speciality;

    /**
     * 图片地址
     */
    @ApiModelProperty("图片地址")
    private String imgUrl;

    /**
     * 教练会员等级id
     */
    @ApiModelProperty("教练会员等级id")
    private String coachLevelId;

    /**
     * 星级
     */
    @ApiModelProperty("星级")
    private String starLevel;


    /**
     * 是否是金牌教练
     */
    @ApiModelProperty("是否是金牌教练,true代表是")
    private Boolean isGold;

    /**
     * 0代表非金牌教练，非0是金牌教练，数字越大越优先显示
     */
    @ApiModelProperty("0代表非金牌教练，非0是金牌教练，数字越大越优先显示")
    private String goldLevel;

    /**
     * 排序
     */
    @ApiModelProperty("排序")
    private String sortIndex;

    /**
     * 教龄（年）
     */
    @ApiModelProperty("教龄（年）")
    private Integer teachAge;

    /**
     * 最初执教时间（年）
     */
    @ApiModelProperty("最初执教时间（年）")
    private Integer startYear;

    /**
     * 获奖数量
     */
    @ApiModelProperty("获奖数量")
    private Integer awardNum;

    /**
     * 证书数量
     */
    @ApiModelProperty("证书数量")
    private String ccieNum;

    /**
     * 课程数量
     */
    @ApiModelProperty("课程数量")
    private String classNum;

    /**
     * 城市id
     */
    @ApiModelProperty("城市id")
    private Long cityId;

    private StoreVo storeVo;
}
