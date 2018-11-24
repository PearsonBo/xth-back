package com.xth.controller.client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.xth.controller.AjaxBaseController;
import com.xth.model.base.PackVo;
import com.xth.model.constant.CommonConstant;
import com.xth.model.so.client.ClientCouponSo;
import com.xth.model.vo.client.ClientCouponVo;
import com.xth.service.RedisReadHelper;
import com.xth.service.client.additional.ClientCouponAdditionalService;
import com.xth.service.client.common.ClientCouponService;
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
 * @date 2018/09/26
 */
@RestController
@RequestMapping(ClientCouponController.VIEW_PREFIX)
@Api(tags = {"客户拥有的优惠券接口"})
public class ClientCouponController extends AjaxBaseController {

    public static final String VIEW_PREFIX = "/rest/clientCoupon";
    private static final String RECEIVE_COUPON = "/receiveCoupon";

    @Autowired
    private RedisReadHelper redisReadHelper;

    @Autowired
    private ClientCouponService clientCouponService;

    @Autowired
    private ClientCouponAdditionalService clientCouponAdditionalService;


    @RequestMapping(value = CREATE, method = RequestMethod.POST)
    public PackVo create(@RequestBody ClientCouponVo clientCoupon) {
        clientCouponService.create(clientCoupon);
        return new PackVo();
    }

    @RequestMapping(value = DELETE, method = RequestMethod.GET)
    public PackVo delete(Long id) {
        clientCouponService.delete(id);
        return new PackVo();
    }

    @RequestMapping(value = UPDATE, method = RequestMethod.POST)
    public PackVo update(@RequestBody ClientCouponVo clientCoupon) {
        clientCouponService.update(clientCoupon);
        return new PackVo();
    }

    @RequestMapping(value = FIND, method = RequestMethod.GET)
    @HystrixCommand(commandKey = "ClientCoupon")
    public PackVo<ClientCouponVo> find(Long id) {
        PackVo<ClientCouponVo> packVo = new PackVo<>();
        ClientCouponVo clientCouponVo = clientCouponService.find(id);
        packVo.setVo(clientCouponVo);
        return packVo;
    }

    @RequestMapping(value = LIST_PAGINATION, method = RequestMethod.POST)
    @HystrixCommand(commandKey = "ClientCoupon")
    public PackVo<ClientCouponVo> listPagination(@RequestBody ClientCouponSo clientCouponSo, HttpServletRequest request) {
        PackVo<ClientCouponVo> packVo = new PackVo<>();
        String thirdSession = request.getHeader(CommonConstant.THIRD_SESSION);
        String clientId = redisReadHelper.getStringInRedis(thirdSession);
        clientCouponSo.setClientId(Long.parseLong(clientId));
        packVo.setPageList(clientCouponService.listPagination(clientCouponSo));
        return packVo;
    }

    @RequestMapping(value = RECEIVE_COUPON, method = RequestMethod.POST)
    @ApiOperation(value = "客户领取优惠券", notes = "优惠券couponId为必填参数")
    @HystrixCommand(commandKey = "ClientCoupon")
    public PackVo receiveCoupon(@RequestBody ClientCouponVo clientCouponVo, HttpServletRequest request) {
        String thirdSession = request.getHeader(CommonConstant.THIRD_SESSION);
        String clientId = redisReadHelper.getStringInRedis(thirdSession);
        clientCouponVo.setClientId(Long.parseLong(clientId));
        clientCouponService.receiveCoupon(clientCouponVo);
        return new PackVo();
    }


}
