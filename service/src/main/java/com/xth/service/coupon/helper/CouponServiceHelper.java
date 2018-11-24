package com.xth.service.coupon.helper;

import com.google.common.collect.Lists;
import com.xth.dao.coupon.CouponDao;
import com.xth.model.enums.CouponApplyScope;
import com.xth.model.enums.DiscountType;
import com.xth.model.vo.city.CityVo;
import com.xth.model.vo.coupon.CouponVo;
import com.xth.model.vo.store.StoreVo;
import com.xth.service.AbstractNoTransactionalService;
import com.xth.service.city.helper.CityServiceHelper;
import com.xth.service.store.helper.StoreServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 系统生成Service帮助类
 *
 * @author admin
 * @date 2018/09/25
 */
@Service
public class CouponServiceHelper extends AbstractNoTransactionalService {

    @Autowired
    private CouponDao couponDao;


    @Autowired
    private CityServiceHelper cityServiceHelper;

    @Autowired
    private StoreServiceHelper storeServiceHelper;

    public void fillCoupon(CouponVo couponVo) {
        if (couponVo.getCouponApplyScope() == CouponApplyScope.CITY && couponVo.getApplyCityId() != null) {
            CityVo city = cityServiceHelper.findCityByCityId(couponVo.getApplyCityId());
            couponVo.setApplyCityName(city == null ? null : city.getName());
            couponVo.setApplyScopeContent(city == null ? null : city.getName());
        }

        if (couponVo.getCouponApplyScope() == CouponApplyScope.STORE && couponVo.getApplyStoreId() != null) {
            StoreVo store = storeServiceHelper.findStoreByStoreId(couponVo.getApplyStoreId());
            couponVo.setApplyStoreName(store == null ? null : store.getName());
            couponVo.setApplyScopeContent(store == null ? null : store.getName());
        }

        if (couponVo.getType() == DiscountType.FULL_REDUCTION) {
            couponVo.setContentList(Lists.newArrayList(couponVo.getContent().split("\\|")));
        }
    }
}
