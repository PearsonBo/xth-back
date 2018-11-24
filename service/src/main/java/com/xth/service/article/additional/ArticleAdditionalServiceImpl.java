package com.xth.service.article.additional;

import com.xth.dao.article.ArticleDao;
import com.xth.service.AbstractTransactionalService;
import com.xth.service.article.helper.ArticleServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统生成AdditionalServiceImpl
 *
 * @author admin
 * @date 2018/10/04
 */
@Service
public class ArticleAdditionalServiceImpl extends AbstractTransactionalService implements ArticleAdditionalService {

    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private ArticleServiceHelper articleServiceHelper;


}
