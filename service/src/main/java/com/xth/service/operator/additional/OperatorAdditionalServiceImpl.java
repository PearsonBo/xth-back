package com.xth.service.operator.additional;

import com.xth.dao.operator.OperatorDao;
import com.xth.service.AbstractTransactionalService;
import com.xth.service.operator.helper.OperatorServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统生成AdditionalServiceImpl
 *
 * @author admin
 * @date 2018/10/08
 */
@Service
public class OperatorAdditionalServiceImpl extends AbstractTransactionalService implements OperatorAdditionalService {

    @Autowired
    private OperatorDao operatorDao;
    @Autowired
    private OperatorServiceHelper operatorServiceHelper;


}
