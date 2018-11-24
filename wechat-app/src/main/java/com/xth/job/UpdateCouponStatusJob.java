package com.xth.job;

import com.xth.dao.coupon.CouponDao;
import com.xth.model.bo.coupon.Coupon;
import com.xth.model.so.coupon.CouponSo;
import com.xth.model.vo.coupon.CouponVo;
import com.xth.util.DozerHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;


/**
 * @Author: Hu Jianbo
 * @Date: 2018/10/14 0027 下午 22:19
 */
@Slf4j
@Component
public class UpdateCouponStatusJob {

    @Autowired
    private CouponDao couponDao;

    @Autowired
    private DozerHelper dozerHelper;

    /**
     * 每天凌晨更新优惠券状态
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void updateCouponStatus() {
        log.info("每日更新优惠券状态job开始");
        CouponSo couponSo = new CouponSo();
        List<CouponVo> couponVoList = couponDao.listVoBySo(couponSo);
        if (CollectionUtils.isEmpty(couponVoList)) {
            log.info("系统中无优惠券");
            return;
        }
        for (CouponVo couponVo : couponVoList) {
            if (couponVo.getExpireTime().getTime() < System.currentTimeMillis()) {
                log.info(String.format("优惠券【%s】过期", couponVo.getName()));
                couponVo.setAvailable(Boolean.FALSE);
                couponDao.update(dozerHelper.convert(couponVo, Coupon.class));
            }
        }
        log.info("每日更新优惠券状态job结束");
    }

}
