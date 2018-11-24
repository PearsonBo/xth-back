package com.xth.model.bo.city;

import com.xth.model.base.AbstractBo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: Hu Jianbo
 * @Date: 2018/9/26 0010 下午 22:12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "城市")
public class City extends AbstractBo {

    /**
     * 类型，1：省份或者直辖市，2：城市
     */
    @ApiModelProperty("类型")
    private Integer type;

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;

    /**
     * 简称
     */
    @ApiModelProperty("简称")
    private String shortName;

}
