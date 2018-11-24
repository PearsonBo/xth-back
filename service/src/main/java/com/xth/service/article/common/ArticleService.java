package com.xth.service.article.common;


import com.xth.model.base.PageList;
import com.xth.model.so.article.ArticleSo;
import com.xth.model.vo.article.ArticleVo;

/**
 * 系统生成Service类
 *
 * @author admin
 * @date 2018/10/04
 */
public interface ArticleService {

    /**
     * 新增
     *
     * @param article 新增对象
     * @return 新增对象的id
     */
    Long create(ArticleVo article);

    /**
     * 删除
     *
     * @param id 删除对象的id
     */
    void delete(Long id);

    /**
     * 修改
     *
     * @param article 修改对象
     */
    void update(ArticleVo article);

    /**
     * 单个查询
     *
     * @param id 查询对象id
     * @return 查询结果
     */
    ArticleVo find(Long id);

    /**
     * 按条件查询
     *
     * @param articleSo 查询条件
     * @return 查询结果
     */
    PageList<ArticleVo> listPagination(ArticleSo articleSo);


}

