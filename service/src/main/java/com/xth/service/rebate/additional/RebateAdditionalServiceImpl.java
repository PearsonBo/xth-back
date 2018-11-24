package com.xth.service.rebate.additional;

import com.xth.dao.rebate.RebateDao;
import com.xth.service.AbstractTransactionalService;
import com.xth.service.rebate.helper.RebateServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统生成AdditionalServiceImpl
 *
 * @author admin
 * @date 2018/09/29
 */
@Service
public class RebateAdditionalServiceImpl extends AbstractTransactionalService implements RebateAdditionalService {

    @Autowired
    private RebateDao rebateDao;
    @Autowired
    private RebateServiceHelper rebateServiceHelper;


}
