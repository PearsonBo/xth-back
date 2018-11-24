package com.xth.service.client.export;

import com.xth.dao.client.ClientCouponDao;
import com.xth.model.so.client.ClientCouponSo;
import com.xth.model.vo.client.ClientCouponVo;
import com.xth.service.BaseExport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统生成导出类
 *
 * @author admin
 * @date 2018/09/26
 */
@Service
public class ExportClientCoupon extends BaseExport<ClientCouponSo> {

    private static final String TEMPLATE = "T_CLIENT_COUPON.xml";
    private static final String STORE_NAME = "T_CLIENT_COUPON";

    @Autowired
    private ClientCouponDao clientCouponDao;


}
