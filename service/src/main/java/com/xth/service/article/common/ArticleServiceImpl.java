package com.xth.service.article.common;

import com.xth.dao.article.ArticleDao;
import com.xth.model.base.AbstractVo;
import com.xth.model.base.PageList;
import com.xth.model.bo.article.Article;
import com.xth.model.so.article.ArticleSo;
import com.xth.model.vo.article.ArticleVo;
import com.xth.service.AbstractTransactionalService;
import com.xth.util.MarkdownUtil;
import org.apache.commons.lang3.StringUtils;
import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;


/**
 * 系统生成Service实现类
 *
 * @author admin
 * @date 2018/10/04
 */
@Service
public class ArticleServiceImpl extends AbstractTransactionalService implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    /**
     * 新增
     *
     * @param article 新增对象
     * @return 新增对象的id
     */
    @Override
    public Long create(ArticleVo article) {
        checkBeforeCreate(article);
        Article articleBo = dozer.convert(article, Article.class);
        return articleDao.insert(articleBo);
    }

    private void checkBeforeCreate(ArticleVo article) {
        checkRequired(article);
        checkDuplicate4Create(article);
    }


    /**
     * 删除
     *
     * @param id 删除对象的id
     */
    @Override
    public void delete(Long id) {
        articleDao.delete(id);
    }

    /**
     * 修改
     *
     * @param article 修改对象
     */
    @Override
    public void update(ArticleVo article) {
        checkBeforeUpdate(article);
        Article articleBo = dozer.convert(article, Article.class);
        articleDao.update(articleBo);
    }

    private void checkBeforeUpdate(ArticleVo article) {
        checkRequired(article);
        checkDuplicate4Update(article);
    }

    /**
     * 单个查询
     *
     * @param id 查询对象id
     * @return 查询结果
     */
    @Override
    public ArticleVo find(Long id) {
        ArticleVo vo = articleDao.findVo(id);
        article(vo);
        vo.setHits(vo.getHits() == null ? 0 : vo.getHits() + 1);
        return vo;
    }

    /**
     * 按条件查询
     *
     * @param articleSo 查询条件
     * @return 查询结果
     */
    @Override
    public PageList<ArticleVo> listPagination(ArticleSo articleSo) {
        List<ArticleVo> list = articleDao.listPaginationVoBySo(articleSo);

        for (ArticleVo articleVo : list) {
            article(articleVo);
        }
        int count = articleDao.countBySo(articleSo);
        return new PageList<>(list, count);
    }

    /**
     * 显示文章内容，转换markdown为html
     *
     * @param value
     * @return
     */
    public void article(ArticleVo articleVo) {
        String value = articleVo.getContent();
        if (StringUtils.isNotBlank(value)) {
            value = value.replace("<!--more-->", "\r\n");
            articleVo.setContent(MarkdownUtil.mdToHtml(value));
        }
    }


    /**
     * 检查必填字段
     */
    private void checkRequired(ArticleVo article) {

    }

    /**
     * 检查字段唯一性（创建）
     */
    private void checkDuplicate4Create(ArticleVo article) {

    }


    /**
     * 检查字段唯一性（更新）
     */
    private void checkDuplicate4Update(ArticleVo article) {

    }


}
