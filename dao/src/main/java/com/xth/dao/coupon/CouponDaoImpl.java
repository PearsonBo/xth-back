package com.xth.dao.coupon;

import com.xth.dao.BaseNoHistoryDaoImpl;
import com.xth.mapper.Mapper;
import com.xth.mapper.coupon.CouponMapper;
import com.xth.model.bo.coupon.Coupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 * 系统生成Dao实现类
 *
 * @author admin
 * @date 2018/09/25
 */
@Repository
public class CouponDaoImpl extends BaseNoHistoryDaoImpl<Coupon> implements CouponDao {

    @Autowired
    private CouponMapper mapper;

    @Override
    protected Mapper<Coupon> getMapper() {
        return mapper;
    }


}
