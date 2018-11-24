package com.xth.controller.level;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.xth.controller.AjaxBaseController;
import com.xth.model.base.PackVo;
import com.xth.model.so.level.LevelSo;
import com.xth.model.vo.level.LevelVo;
import com.xth.service.level.additional.LevelAdditionalService;
import com.xth.service.level.common.LevelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统生成Controller
 *
 * @author admin
 * @date 2018/09/24
 */
@RestController
@RequestMapping(LevelController.VIEW_PREFIX)
@Api(value = "等级controller", tags = {"等级接口"})
public class LevelController extends AjaxBaseController {

    public static final String VIEW_PREFIX = "/rest/level";

    @Autowired
    private LevelService levelService;

    @Autowired
    private LevelAdditionalService levelAdditionalService;


    @RequestMapping(value = CREATE, method = RequestMethod.POST)
    public PackVo create(@RequestBody LevelVo level) {
        levelService.create(level);
        return new PackVo();
    }

    @RequestMapping(value = DELETE, method = RequestMethod.GET)
    public PackVo delete(Long id) {
        levelService.delete(id);
        return new PackVo();
    }

    @RequestMapping(value = UPDATE, method = RequestMethod.POST)
    public PackVo update(@RequestBody @ApiParam(name = "等级对象", value = "传入json格式", required = true) LevelVo level) {
        levelService.update(level);
        return new PackVo();
    }

    @RequestMapping(value = FIND, method = RequestMethod.GET)
    @HystrixCommand(commandKey = "Level")
    @ApiOperation(value = "获取等级信息", notes = "注意问题点")
    public PackVo<LevelVo> find(Long id) {
        PackVo<LevelVo> packVo = new PackVo<>();
        LevelVo levelVo = levelService.find(id);
        packVo.setVo(levelVo);
        return packVo;
    }

    @RequestMapping(value = LIST_PAGINATION, method = RequestMethod.POST)
    @HystrixCommand(commandKey = "Level")
    public PackVo<LevelVo> listPagination(@RequestBody LevelSo levelSo) {
        PackVo<LevelVo> packVo = new PackVo<>();
        packVo.setPageList(levelService.listPagination(levelSo));
        return packVo;
    }


}
