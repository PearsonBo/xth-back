package com.xth.controller.company;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.xth.controller.AjaxBaseController;
import com.xth.model.base.PackVo;
import com.xth.model.so.company.CompanySo;
import com.xth.model.vo.company.CompanyVo;
import com.xth.service.company.additional.CompanyAdditionalService;
import com.xth.service.company.common.CompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统生成Controller
 *
 * @author admin
 * @date 2018/09/12
 */
@RestController
@RequestMapping(CompanyController.VIEW_PREFIX)
@Api(tags = {"商户接口"})
public class CompanyController extends AjaxBaseController {

    public static final String VIEW_PREFIX = "/rest/company";

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyAdditionalService companyAdditionalService;


    @RequestMapping(value = CREATE, method = RequestMethod.POST)
    public PackVo create(@RequestBody CompanyVo company) {
        companyService.create(company);
        return new PackVo();
    }

    @RequestMapping(value = DELETE, method = RequestMethod.GET)
    public PackVo delete(Long id) {
        companyService.delete(id);
        return new PackVo();
    }

    @RequestMapping(value = UPDATE, method = RequestMethod.POST)
    public PackVo update(@RequestBody CompanyVo company) {
        companyService.update(company);
        return new PackVo();
    }

    @RequestMapping(value = FIND, method = RequestMethod.GET)
    @HystrixCommand(commandKey = "Company")
    @ApiOperation(value = "获取商户信息", notes = "注意问题点")
    public PackVo<CompanyVo> find(Long id) {
        PackVo<CompanyVo> packVo = new PackVo<>();
        CompanyVo companyVo = companyService.find(id);
        packVo.setVo(companyVo);
        return packVo;
    }

    @RequestMapping(value = LIST_PAGINATION, method = RequestMethod.POST)
    @HystrixCommand(commandKey = "Company")
    @ApiOperation(value = "获取商家列表", notes = "查询条件为空，展示全部")
    public PackVo<CompanyVo> listPagination(@RequestBody CompanySo companySo) {
        PackVo<CompanyVo> packVo = new PackVo<>();
        packVo.setPageList(companyService.listPagination(companySo));
        return packVo;
    }


}
