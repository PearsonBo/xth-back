package com.xth.controller.rebate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.xth.controller.AjaxBaseController;
import com.xth.model.base.PackVo;
import com.xth.model.constant.CommonConstant;
import com.xth.model.so.rebate.RebateSo;
import com.xth.model.vo.rebate.RebateVo;
import com.xth.service.RedisReadHelper;
import com.xth.service.rebate.additional.RebateAdditionalService;
import com.xth.service.rebate.common.RebateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 系统生成Controller
 *
 * @author admin
 * @date 2018/09/29
 */
@RestController
@RequestMapping(RebateController.VIEW_PREFIX)
@Api(tags = {"返利接口"})
public class RebateController extends AjaxBaseController {

    public static final String VIEW_PREFIX = "/rest/rebate";
    private static final String CAL_RETURN_MONEY = "/calReturnMoney";

    @Autowired
    private RebateService rebateService;

    @Autowired
    private RebateAdditionalService rebateAdditionalService;

    @Autowired
    private RedisReadHelper redisReadHelper;

    @RequestMapping(value = CREATE, method = RequestMethod.POST)
    @ApiOperation(value = "提交返利", notes = "必要参数：storeId,couponId,consumeMoney,returnMoney")
    public PackVo create(@RequestBody RebateVo rebate, HttpServletRequest request) {
        String thirdSession = request.getHeader(CommonConstant.THIRD_SESSION);
        String clientId = redisReadHelper.getStringInRedis(thirdSession);
        rebate.setClientId(Long.parseLong(clientId));
        rebateService.create(rebate);
        return new PackVo();
    }

    @RequestMapping(value = "/weChat/uploadImage", method = {RequestMethod.POST, RequestMethod.GET})
    @ApiOperation(value = "上传图片", notes = "imgType参数用于辨别图片类型，方便图片分开存储。" +
            "枚举有：store、coach、rebate、level、default，非必填，返回服务端图片路径")
    public PackVo uploadImage(HttpServletRequest request,
                              @RequestParam(value = "file", required = false) MultipartFile file,
                              @RequestParam(value = "imgType", required = false) String imgType) throws IOException {
        PackVo<String> packVo = new PackVo<>();
        String path = rebateService.uploadImage(request, file, imgType);
        packVo.setVo(path);
        return packVo;
    }

    @RequestMapping(value = DELETE, method = RequestMethod.GET)
    public PackVo delete(Long id) {
        rebateService.delete(id);
        return new PackVo();
    }

    @RequestMapping(value = UPDATE, method = RequestMethod.POST)
    public PackVo update(@RequestBody RebateVo rebate) {
        rebateService.update(rebate);
        return new PackVo();
    }

    @RequestMapping(value = FIND, method = RequestMethod.GET)
    @HystrixCommand(commandKey = "Rebate")
    public PackVo<RebateVo> find(Long id) {
        PackVo<RebateVo> packVo = new PackVo<>();
        RebateVo rebateVo = rebateService.find(id);
        packVo.setVo(rebateVo);
        return packVo;
    }

    @RequestMapping(value = LIST_PAGINATION, method = RequestMethod.POST)
    @HystrixCommand(commandKey = "Rebate")
    @ApiOperation(value = "用户返利订单列表", notes = "用户返利订单列表")
    public PackVo<RebateVo> listPagination(@RequestBody RebateSo rebateSo, HttpServletRequest request) {
        PackVo<RebateVo> packVo = new PackVo<>();
        String thirdSession = request.getHeader(CommonConstant.THIRD_SESSION);
        String clientId = redisReadHelper.getStringInRedis(thirdSession);
        rebateSo.setClientId(Long.parseLong(clientId));
        packVo.setPageList(rebateService.listPagination(rebateSo));
        return packVo;
    }

    @RequestMapping(value = CAL_RETURN_MONEY, method = RequestMethod.POST)
    @ApiOperation(value = "计算返利金额", notes = "计算返利金额，必要参数：storeId,couponId,consumeMoney")
    public PackVo calRebate(@RequestBody RebateVo rebateVo) {
        PackVo<String> packVo = new PackVo<>();
        packVo.setVo(rebateService.calRebate(rebateVo));
        return packVo;
    }


}
