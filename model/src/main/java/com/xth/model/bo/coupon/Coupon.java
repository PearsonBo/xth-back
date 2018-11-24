package com.xth.model.bo.coupon;

import com.xth.model.base.AbstractBo;
import com.xth.model.enums.CouponApplyScope;
import com.xth.model.enums.DiscountType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 优惠券
 *
 * @Author: Hu Jianbo
 * @Date: 2018/9/25 0010 下午 22:10
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Coupon extends AbstractBo {
    /**
     * 活动名称
     */
    @ApiModelProperty("活动名称")
    private String name;

    /**
     * 优惠类型
     */
    @ApiModelProperty("优惠类型")
    private DiscountType type;

    /**
     * 如果是比率折扣，保存的是数字；如果是满减，保存的是500|100，满500减100
     */
    @ApiModelProperty("如果是比率折扣，保存的是数字；如果是满减，保存的是500|100，满500减100")
    private String content;

    /**
     * 生效时间
     */
    @ApiModelProperty("生效时间")
    private Date effectTime;

    /**
     * 过期时间
     */
    @ApiModelProperty("过期时间")
    private Date expireTime;

    /**
     * 适用范围
     */
    @ApiModelProperty("适用范围")
    private CouponApplyScope couponApplyScope;

    /**
     * 适用的省份id，只有在适用范围是某个省份才有值
     */
    @ApiModelProperty("适用的省份id，只有在适用范围是某个省份才有值")
    private Long applyProvinceId;

    /**
     * 适用的城市id，只有在适用范围是某个城市才有值
     */
    @ApiModelProperty("适用的城市id，只有在适用范围是某个城市才有值")
    private Long applyCityId;

    /**
     * 适用的商户id，只有在适用范围是某个商户才有值
     */
    @ApiModelProperty("适用的商户id，只有在适用范围是某个商户才有值")
    private Long applyCompanyId;

    /**
     * 适用的场馆id，只有在适用范围是某个场馆才有值
     */
    @ApiModelProperty("适用的场馆id，只有在适用范围是某个场馆才有值")
    private Long applyStoreId;

    /**
     * 是否可用
     */
    @ApiModelProperty("是否可用")
    private Boolean available;

    /**
     * 城市id
     */
    @ApiModelProperty("城市id")
    private Long cityId;

}
