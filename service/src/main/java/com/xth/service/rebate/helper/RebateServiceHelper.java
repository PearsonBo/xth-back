package com.xth.service.rebate.helper;

import com.xth.dao.rebate.RebateDao;
import com.xth.service.AbstractNoTransactionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 系统生成Service帮助类
 *
 * @author admin
 * @date 2018/09/29
 */
@Service
public class RebateServiceHelper extends AbstractNoTransactionalService {

    @Autowired
    private RebateDao rebateDao;


}
