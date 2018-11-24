package com.xth.controller.client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.xth.controller.AjaxBaseController;
import com.xth.model.base.PackVo;
import com.xth.model.constant.CommonConstant;
import com.xth.model.so.city.LocationSo;
import com.xth.model.so.client.ClientSo;
import com.xth.model.vo.city.CityVo;
import com.xth.model.vo.client.ClientVo;
import com.xth.model.vo.client.MobileVo;
import com.xth.service.CheckEmptyHelper;
import com.xth.service.RedisReadHelper;
import com.xth.service.client.additional.ClientAdditionalService;
import com.xth.service.client.common.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * @date 2018/09/18
 */
@RestController
@RequestMapping(ClientController.VIEW_PREFIX)
@Api(tags = {"客户接口"})
public class ClientController extends AjaxBaseController {

    protected static Logger logger = LoggerFactory.getLogger(ClientController.class);

    public static final String VIEW_PREFIX = "/rest/client";
    private static final String FILL_MOBILE = "/fillMobile";
    private static final String FILL_ADDRESS_BY_CITY_ID = "/fillAddressByCityId";
    private static final String FILL_ADDRESS_BY_DETAIL = "/fillAddressByDetail";

    @Autowired
    private RedisReadHelper redisReadHelper;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientAdditionalService clientAdditionalService;


    @RequestMapping(value = CREATE, method = RequestMethod.POST)
    public PackVo create(@RequestBody ClientVo client) {
        clientService.create(client);
        return new PackVo();
    }

    @RequestMapping(value = DELETE, method = RequestMethod.GET)
    public PackVo delete(Long id) {
        clientService.delete(id);
        return new PackVo();
    }

    @RequestMapping(value = UPDATE, method = RequestMethod.POST)
    public PackVo update(@RequestBody ClientVo client) {
        clientService.update(client);
        return new PackVo();
    }

    @RequestMapping(value = FIND, method = RequestMethod.GET)
    @HystrixCommand(commandKey = "Client")
    @ApiOperation(value = "查询用户信息", notes = "查询用户信息")
    public PackVo<ClientVo> find(HttpServletRequest request) {
        PackVo<ClientVo> packVo = new PackVo<>();
        String thirdSession = request.getHeader(CommonConstant.THIRD_SESSION);
        String clientId = redisReadHelper.getStringInRedis(thirdSession);
        CheckEmptyHelper.checkString("请求头未传third-session", clientId);
        ClientVo clientVo = clientService.find(Long.parseLong(clientId));
        packVo.setVo(clientVo);
        return packVo;
    }

    @RequestMapping(value = LIST_PAGINATION, method = RequestMethod.POST)
    @HystrixCommand(commandKey = "Client")
    public PackVo<ClientVo> listPagination(@RequestBody ClientSo clientSo) {
        PackVo<ClientVo> packVo = new PackVo<>();
        packVo.setPageList(clientService.listPagination(clientSo));
        return packVo;
    }

    @RequestMapping(value = FILL_MOBILE, method = RequestMethod.POST)
    @ApiOperation(value = "保存用户认证的手机号", notes = "必要参数，encryptedData, iv")
    public PackVo fillMobile(@RequestBody MobileVo mobileVo,
                             HttpServletRequest request) {
        String thirdSession = request.getHeader(CommonConstant.THIRD_SESSION);
        String clientId = redisReadHelper.getStringInRedis(thirdSession);
        logger.info("thirdSession" + thirdSession);
        clientService.fillMobile(thirdSession, clientId, mobileVo.getEncryptedData(), mobileVo.getIv());
        return new PackVo();
    }

    @RequestMapping(value = FILL_ADDRESS_BY_CITY_ID, method = RequestMethod.POST)
    @ApiOperation(value = "根据城市id,保存用户最近登录地址", notes = "城市id")
    public PackVo fillAddressByCityId(@RequestBody LocationSo locationSo, HttpServletRequest request) {
        PackVo<CityVo> packVo = new PackVo<>();
        String thirdSession = request.getHeader(CommonConstant.THIRD_SESSION);
        String clientId = redisReadHelper.getStringInRedis(thirdSession);
        locationSo.setClientId(Long.parseLong(clientId));
        packVo.setVo(clientService.fillAddressByCityId(locationSo));
        return packVo;
    }

    @RequestMapping(value = FILL_ADDRESS_BY_DETAIL, method = RequestMethod.POST)
    @ApiOperation(value = "根据用户详细经纬度,保存用户最近登录地址", notes = "longitude：经度，latitude：纬度")
    public PackVo fillAddressByDetail(@RequestBody LocationSo locationSo, HttpServletRequest request) {
        PackVo<CityVo> packVo = new PackVo<>();
        String thirdSession = request.getHeader(CommonConstant.THIRD_SESSION);
        String clientId = redisReadHelper.getStringInRedis(thirdSession);
        locationSo.setClientId(Long.parseLong(clientId));
        packVo.setVo(clientService.fillAddressByDetail(locationSo));
        return packVo;
    }
}
