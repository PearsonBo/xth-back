package com.xth.service.client.additional;

import com.xth.dao.client.ClientCouponDao;
import com.xth.service.AbstractTransactionalService;
import com.xth.service.client.helper.ClientCouponServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统生成AdditionalServiceImpl
 *
 * @author admin
 * @date 2018/09/26
 */
@Service
public class ClientCouponAdditionalServiceImpl extends AbstractTransactionalService implements ClientCouponAdditionalService {

    @Autowired
    private ClientCouponDao clientCouponDao;
    @Autowired
    private ClientCouponServiceHelper clientCouponServiceHelper;


}
