package com.xth.dao.client;

import com.xth.dao.BaseNoHistoryDaoImpl;
import com.xth.mapper.Mapper;
import com.xth.mapper.client.ClientCouponMapper;
import com.xth.model.bo.client.ClientCoupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 * 系统生成Dao实现类
 *
 * @author admin
 * @date 2018/09/26
 */
@Repository
public class ClientCouponDaoImpl extends BaseNoHistoryDaoImpl<ClientCoupon> implements ClientCouponDao {

    @Autowired
    private ClientCouponMapper mapper;

    @Override
    protected Mapper<ClientCoupon> getMapper() {
        return mapper;
    }


}
