package com.xth.model.vo.client;

import com.xth.model.base.AbstractVo;
import com.xth.model.vo.coupon.CouponVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 优惠券
 *
 * @Author: Hu Jianbo
 * @Date: 2018/9/25 0010 下午 22:10
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ClientCouponVo extends AbstractVo {
    /**
     * 客户id
     */
    @ApiModelProperty("客户id")
    private Long clientId;

    /**
     * 优惠券id
     */
    @ApiModelProperty("优惠券id")
    private Long couponId;

    /**
     * 是否使用过
     */
    @ApiModelProperty("是否使用过")
    private Boolean isUsed;

    /**
     * 是否可用
     */
    @ApiModelProperty("是否可用")
    private Boolean available;

    /**
     * 优惠券信息
     */
    @ApiModelProperty("优惠券信息")
    private CouponVo couponVo;
}
