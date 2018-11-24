package com.xth.service.article.export;

import com.xth.dao.article.ArticleDao;
import com.xth.model.so.article.ArticleSo;
import com.xth.model.vo.article.ArticleVo;
import com.xth.service.BaseExport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统生成导出类
 *
 * @author admin
 * @date 2018/10/04
 */
@Service
public class ExportArticle extends BaseExport<ArticleSo> {

    private static final String TEMPLATE = "T_ARTICLE.xml";
    private static final String STORE_NAME = "T_ARTICLE";

    @Autowired
    private ArticleDao articleDao;


}
