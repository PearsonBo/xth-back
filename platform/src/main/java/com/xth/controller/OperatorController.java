package com.xth.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.xth.model.base.PackVo;
import com.xth.model.so.operator.OperatorSo;
import com.xth.model.vo.operator.OperatorVo;
import com.xth.service.operator.additional.OperatorAdditionalService;
import com.xth.service.operator.common.OperatorService;
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
 * @date 2018/10/08
 */
@RestController
@RequestMapping(OperatorController.VIEW_PREFIX)
@Api(tags = {"操作人接口"})
public class OperatorController extends AjaxBaseController {

    public static final String VIEW_PREFIX = "/rest/operator";

    @Autowired
    private OperatorService operatorService;

    @Autowired
    private OperatorAdditionalService operatorAdditionalService;


    @RequestMapping(value = CREATE, method = RequestMethod.POST)
    public PackVo create(@RequestBody OperatorVo operator) {
        operatorService.create(operator);
        return new PackVo();
    }

    @RequestMapping(value = DELETE, method = RequestMethod.GET)
    public PackVo delete(Long id) {
        operatorService.delete(id);
        return new PackVo();
    }

    @RequestMapping(value = UPDATE, method = RequestMethod.POST)
    public PackVo update(@RequestBody OperatorVo operator) {
        operatorService.update(operator);
        return new PackVo();
    }

    @RequestMapping(value = FIND, method = RequestMethod.GET)
    @HystrixCommand(commandKey = "Operator")
    public PackVo<OperatorVo> find(Long id) {
        PackVo<OperatorVo> packVo = new PackVo<>();
        OperatorVo operatorVo = operatorService.find(id);
        packVo.setVo(operatorVo);
        return packVo;
    }

    @RequestMapping(value = LIST_PAGINATION, method = RequestMethod.POST)
    @HystrixCommand(commandKey = "Operator")
    public PackVo<OperatorVo> listPagination(@RequestBody OperatorSo operatorSo) {
        PackVo<OperatorVo> packVo = new PackVo<>();
        packVo.setPageList(operatorService.listPagination(operatorSo));
        return packVo;
    }


}
