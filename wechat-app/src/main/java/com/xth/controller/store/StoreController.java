package com.xth.controller.store;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.xth.controller.AjaxBaseController;
import com.xth.model.base.PackVo;
import com.xth.model.constant.CommonConstant;
import com.xth.model.so.store.StoreSo;
import com.xth.model.vo.store.StoreVo;
import com.xth.service.RedisReadHelper;
import com.xth.service.store.additional.StoreAdditionalService;
import com.xth.service.store.common.StoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统生成Controller
 *
 * @author admin
 * @date 2018/09/16
 */
@RestController
@RequestMapping(StoreController.VIEW_PREFIX)
@Api(tags = {"场馆接口"})
public class StoreController extends AjaxBaseController {

    public static final String VIEW_PREFIX = "/rest/store";

    @Autowired
    private RedisReadHelper redisReadHelper;

    @Autowired
    private StoreService storeService;

    @Autowired
    private StoreAdditionalService storeAdditionalService;


    @RequestMapping(value = CREATE, method = RequestMethod.POST)
    public PackVo create(@RequestBody StoreVo store) {
        storeService.create(store);
        return new PackVo();
    }

    @RequestMapping(value = DELETE, method = RequestMethod.GET)
    public PackVo delete(Long id) {
        storeService.delete(id);
        return new PackVo();
    }

    @RequestMapping(value = UPDATE, method = RequestMethod.POST)
    public PackVo update(@RequestBody StoreVo store) {
        storeService.update(store);
        return new PackVo();
    }

    @RequestMapping(value = FIND, method = RequestMethod.GET)
    @HystrixCommand(commandKey = "Store")
    @ApiOperation(value = "获取场馆信息", notes = "根据场馆id查询场馆详细信息")
    public PackVo<StoreVo> find(Long id) {
        PackVo<StoreVo> packVo = new PackVo<>();
        StoreVo storeVo = storeService.find(id);
        packVo.setVo(storeVo);
        return packVo;
    }

    @RequestMapping(value = LIST_PAGINATION, method = RequestMethod.POST)
    @ApiOperation(value = "获取场馆列表", notes = "查询条件为空，展示全部；{\"isHot\":true}查询所有热门场馆")
    public PackVo<StoreVo> listPagination(@RequestBody StoreSo storeSo, HttpServletRequest request) {
        PackVo<StoreVo> packVo = new PackVo<>();
        String thirdSession = request.getHeader(CommonConstant.THIRD_SESSION);
        String clientId = redisReadHelper.getStringInRedis(thirdSession);
        if (clientId != null) {
            storeSo.setClientId(Long.parseLong(clientId));
        }
        packVo.setPageList(storeService.listPagination4Weapp(storeSo));
        return packVo;
    }


}
