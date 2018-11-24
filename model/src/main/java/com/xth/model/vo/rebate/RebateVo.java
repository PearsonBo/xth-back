package com.xth.model.vo.rebate;

import com.xth.model.base.AbstractVo;
import com.xth.model.enums.RebateStatusEnum;
import com.xth.model.vo.store.StoreVo;
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
public class RebateVo extends AbstractVo {

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
     * 场馆Name
     */
    @ApiModelProperty("场馆Name")
    private String storeName;

    /**
     * 场馆返利
     */
    @ApiModelProperty("场馆返利")
    private String storeDiscountContent;

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
     * 使用的优惠券内容
     */
    @ApiModelProperty("使用的优惠券内容")
    private String couponContent;

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

    /**
     * 场馆对象
     */
    @ApiModelProperty("场馆对象")
    private StoreVo storeVo;

}
