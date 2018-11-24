package com.xth.service.article.helper;

import com.xth.dao.article.ArticleDao;
import com.xth.service.AbstractNoTransactionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 系统生成Service帮助类
 *
 * @author admin
 * @date 2018/10/04
 */
@Service
public class ArticleServiceHelper extends AbstractNoTransactionalService {

    @Autowired
    private ArticleDao articleDao;


}
