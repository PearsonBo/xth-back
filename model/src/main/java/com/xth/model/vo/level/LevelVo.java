package com.xth.model.vo.level;

import com.xth.model.base.AbstractVo;
import com.xth.model.enums.LevelType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 等级
 *
 * @Author: Hu Jianbo
 * @Date: 2018/9/10 0010 下午 22:10
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LevelVo extends AbstractVo {
    /**
     * 等级名称
     */
    @ApiModelProperty("等级名称")
    private String name;

    /**
     * 等级类型
     */
    @ApiModelProperty("等级类型")
    private LevelType type;

    /**
     * 等级图标地址
     */
    @ApiModelProperty("等级图标地址")
    private String url;


}
