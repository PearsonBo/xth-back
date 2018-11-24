package com.xth.service.store.additional;

import com.xth.dao.store.StoreDao;
import com.xth.service.AbstractTransactionalService;
import com.xth.service.store.helper.StoreServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统生成AdditionalServiceImpl
 *
 * @author admin
 * @date 2018/09/16
 */
@Service
public class StoreAdditionalServiceImpl extends AbstractTransactionalService implements StoreAdditionalService {

    @Autowired
    private StoreDao storeDao;
    @Autowired
    private StoreServiceHelper storeServiceHelper;


}
