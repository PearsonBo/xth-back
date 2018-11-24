package com.xth.model.base;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.List;

/**
 * 基础搜索类
 *
 * @author bl02780
 */
@Data
public class AbstractSo implements Serializable {

    private static final long serialVersionUID = 4009650342175211289L;

    /**
     * 默认的页面数
     */
    public static final Integer DEFAULT_PAGE_SIZE = 50;

    /**
     * 创建人id
     */
    private Long creatorId;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 更新人id
     */
    private Long updaterId;

    /**
     * 更新人
     */
    private String updater;


    /**
     * 当前页面数
     */
    private Integer pageNumber = 1;

    /**
     * 页面数
     */
    private Integer pageSize = DEFAULT_PAGE_SIZE;

    /**
     * objectId list
     */
    private List<Long> idList;

    /**
     * id
     */
    private Long id;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }
}
