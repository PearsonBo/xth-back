package com.xth.model.so.city;

import com.xth.model.base.AbstractSo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: Hu Jianbo
 * @Date: 2018/9/27 0027 上午 0:12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "位置")
public class LocationSo extends AbstractSo {

    /**
     * 客户id
     */
    @ApiModelProperty("客户id")
    private Long clientId;

    /**
     * 城市id
     */
    @ApiModelProperty("城市id")
    private Long cityId;

    /**
     * 经度
     */
    @ApiModelProperty("经度")
    private String longitude;


    /**
     * 纬度
     */
    @ApiModelProperty("纬度")
    private String latitude;

}
