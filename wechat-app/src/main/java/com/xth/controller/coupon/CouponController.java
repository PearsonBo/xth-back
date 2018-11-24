package com.xth.controller.coupon;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.xth.controller.AjaxBaseController;
import com.xth.model.base.PackVo;
import com.xth.model.so.coupon.CouponSo;
import com.xth.model.vo.coupon.CouponVo;
import com.xth.service.coupon.additional.CouponAdditionalService;
import com.xth.service.coupon.common.CouponService;
import com.xth.service.coupon.export.ExportCoupon;
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
 * @date 2018/09/25
 */
@RestController
@RequestMapping(CouponController.VIEW_PREFIX)
@Api(tags = {"优惠券接口"})
public class CouponController extends AjaxBaseController {

    public static final String VIEW_PREFIX = "/rest/coupon";

    @Autowired
    private CouponService couponService;

    @Autowired
    private CouponAdditionalService couponAdditionalService;

    @Autowired
    private ExportCoupon exportCoupon;

    @RequestMapping(value = CREATE, method = RequestMethod.POST)
    public PackVo create(@RequestBody CouponVo coupon) {
        couponService.create(coupon);
        return new PackVo();
    }

    @RequestMapping(value = DELETE, method = RequestMethod.GET)
    public PackVo delete(Long id) {
        couponService.delete(id);
        return new PackVo();
    }

    @RequestMapping(value = UPDATE, method = RequestMethod.POST)
    public PackVo update(@RequestBody CouponVo coupon) {
        couponService.update(coupon);
        return new PackVo();
    }

    @RequestMapping(value = FIND, method = RequestMethod.GET)
    @HystrixCommand(commandKey = "Coupon")
    public PackVo<CouponVo> find(Long id) {
        PackVo<CouponVo> packVo = new PackVo<>();
        CouponVo couponVo = couponService.find(id);
        packVo.setVo(couponVo);
        return packVo;
    }

    @RequestMapping(value = LIST_PAGINATION, method = RequestMethod.POST)
    @HystrixCommand(commandKey = "Coupon")
    public PackVo<CouponVo> listPagination(@RequestBody CouponSo couponSo) {
        PackVo<CouponVo> packVo = new PackVo<>();
        packVo.setPageList(couponService.listPagination4App(couponSo));
        return packVo;
    }

    @RequestMapping(value = EXPORT, method = RequestMethod.POST)
    public PackVo<CouponVo> export(@RequestBody CouponSo couponSo) throws Exception {
        PackVo<CouponVo> packVo = new PackVo<>();
        exportCoupon.export(couponSo);
        return packVo;
    }
}
