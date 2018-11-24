package com.xth.service.coupon.additional;

import com.xth.dao.coupon.CouponDao;
import com.xth.service.AbstractTransactionalService;
import com.xth.service.coupon.helper.CouponServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统生成AdditionalServiceImpl
 *
 * @author admin
 * @date 2018/09/25
 */
@Service
public class CouponAdditionalServiceImpl extends AbstractTransactionalService implements CouponAdditionalService {

    @Autowired
    private CouponDao couponDao;
    @Autowired
    private CouponServiceHelper couponServiceHelper;


}
