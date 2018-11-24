package com.xth.controller.coach;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.xth.controller.AjaxBaseController;
import com.xth.model.base.PackVo;
import com.xth.model.so.coach.CoachSo;
import com.xth.model.vo.coach.CoachVo;
import com.xth.model.vo.coach.ExportCoachVo;
import com.xth.service.coach.additional.CoachAdditionalService;
import com.xth.service.coach.common.CoachService;
import com.xth.service.coach.export.ExportCoach;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 系统生成Controller
 *
 * @author admin
 * @date 2018/09/17
 */
@RestController
@RequestMapping(CoachController.VIEW_PREFIX)
@Api(tags = {"教练接口"})
public class CoachController extends AjaxBaseController {

    public static final String VIEW_PREFIX = "/rest/coach";

    @Autowired
    private CoachService coachService;

    @Autowired
    private CoachAdditionalService coachAdditionalService;
    @Autowired
    private ExportCoach exportCoach;

    @RequestMapping(value = CREATE, method = RequestMethod.POST)
    public PackVo create(@RequestBody CoachVo coach) {
        coachService.create(coach);
        return new PackVo();
    }

    @RequestMapping(value = DELETE, method = RequestMethod.GET)
    public PackVo delete(Long id) {
        coachService.delete(id);
        return new PackVo();
    }

    @RequestMapping(value = UPDATE, method = RequestMethod.POST)
    public PackVo update(@RequestBody CoachVo coach) {
        coachService.update(coach);
        return new PackVo();
    }

    @RequestMapping(value = FIND, method = RequestMethod.GET)
    @HystrixCommand(commandKey = "Coach")
    @ApiOperation(value = "获取教练信息", notes = "根据教练id查询教练详细信息")
    public PackVo<CoachVo> find(Long id) {
        PackVo<CoachVo> packVo = new PackVo<>();
        CoachVo coachVo = coachService.find(id);
        packVo.setVo(coachVo);
        return packVo;
    }

    @RequestMapping(value = LIST_PAGINATION, method = RequestMethod.POST)
    @HystrixCommand(commandKey = "Coach")
    @ApiOperation(value = "获取教练列表", notes = "查询条件为空，展示全部；{\"isGlod\":true}查询所有金牌教练")
    public PackVo<CoachVo> listPagination(@RequestBody CoachSo coachSo) {
        PackVo<CoachVo> packVo = new PackVo<>();
        packVo.setPageList(coachService.listPagination(coachSo));
        return packVo;
    }

    @RequestMapping(value = EXPORT, method = RequestMethod.POST)
    @ApiOperation(value = "教练导出", notes = "教练导出")
    public PackVo<String> export(@RequestBody CoachSo coachSo) throws Exception {
        PackVo<String> packVo = new PackVo<>();
        packVo.setVo(exportCoach.export(coachSo));
        return packVo;
    }

    @RequestMapping(value = IMPORT, method = RequestMethod.POST)
    @ApiOperation(value = "教练导入", notes = "教练导入")
    public PackVo<ExportCoachVo> batchImport(@RequestParam(value = "file", required = false) MultipartFile file) {
        PackVo<ExportCoachVo> packVo = new PackVo<>();
        coachService.batchImport(file);
        return packVo;
    }
}
