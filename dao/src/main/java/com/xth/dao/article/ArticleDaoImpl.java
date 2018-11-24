package com.xth.dao.article;

import com.xth.dao.BaseNoHistoryDaoImpl;
import com.xth.mapper.Mapper;
import com.xth.mapper.article.ArticleMapper;
import com.xth.model.bo.article.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 * 系统生成Dao实现类
 *
 * @author admin
 * @date 2018/10/04
 */
@Repository
public class ArticleDaoImpl extends BaseNoHistoryDaoImpl<Article> implements ArticleDao {

    @Autowired
    private ArticleMapper mapper;

    @Override
    protected Mapper<Article> getMapper() {
        return mapper;
    }


}
