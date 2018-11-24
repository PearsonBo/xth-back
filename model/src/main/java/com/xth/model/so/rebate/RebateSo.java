package com.xth.model.so.rebate;

import com.xth.model.base.AbstractSo;
import com.xth.model.enums.RebateStatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 返利
 *
 * @Author: Hu Jianbo
 * @Date: 2018/9/28 0028 下午 22:00
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RebateSo extends AbstractSo {

    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    private Long clientId;

    /**
     * 场馆ID
     */
    @ApiModelProperty("场馆ID")
    private Long storeId;

    /**
     * 消费金额
     */
    @ApiModelProperty("消费金额")
    private BigDecimal consumeMoney;

    /**
     * 返回金额
     */
    @ApiModelProperty("返回金额")
    private BigDecimal returnMoney;

    /**
     * 使用的优惠券
     */
    @ApiModelProperty("使用的优惠券")
    private Long couponId;

    /**
     * 附件
     */
    @ApiModelProperty("附件")
    private String attachment;

    /**
     * 返利状态
     */
    @ApiModelProperty("返利状态")
    private RebateStatusEnum status;


}
