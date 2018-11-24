package com.xth.controller.article;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.xth.controller.AjaxBaseController;
import com.xth.model.base.PackVo;
import com.xth.model.so.article.ArticleSo;
import com.xth.model.vo.article.ArticleVo;
import com.xth.service.article.additional.ArticleAdditionalService;
import com.xth.service.article.common.ArticleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统生成Controller
 *
 * @author admin
 * @date 2018/10/04
 */
@RestController
@RequestMapping(ArticleController.VIEW_PREFIX)
@Api(tags = {"文章接口"})
public class ArticleController extends AjaxBaseController {

    public static final String VIEW_PREFIX = "/rest/article";

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleAdditionalService articleAdditionalService;


    @RequestMapping(value = CREATE, method = RequestMethod.POST)
    public PackVo create(@RequestBody ArticleVo article) {
        articleService.create(article);
        return new PackVo();
    }

    @RequestMapping(value = DELETE, method = RequestMethod.GET)
    public PackVo delete(Long id) {
        articleService.delete(id);
        return new PackVo();
    }

    @RequestMapping(value = UPDATE, method = RequestMethod.POST)
    public PackVo update(@RequestBody ArticleVo article) {
        articleService.update(article);
        return new PackVo();
    }

    @RequestMapping(value = FIND, method = RequestMethod.GET)
    @HystrixCommand(commandKey = "Article")
    public PackVo<ArticleVo> find(Long id) {
        PackVo<ArticleVo> packVo = new PackVo<>();
        ArticleVo articleVo = articleService.find(id);
        packVo.setVo(articleVo);
        return packVo;
    }

    @RequestMapping(value = LIST_PAGINATION, method = RequestMethod.POST)
    @HystrixCommand(commandKey = "Article")
    public PackVo<ArticleVo> listPagination(@RequestBody ArticleSo articleSo) {
        PackVo<ArticleVo> packVo = new PackVo<>();
        packVo.setPageList(articleService.listPagination(articleSo));
        return packVo;
    }


}
