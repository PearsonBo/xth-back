package com.xth.service.client.additional;

import com.xth.dao.client.ClientDao;
import com.xth.service.AbstractTransactionalService;
import com.xth.service.client.helper.ClientServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统生成AdditionalServiceImpl
 *
 * @author admin
 * @date 2018/09/18
 */
@Service
public class ClientAdditionalServiceImpl extends AbstractTransactionalService implements ClientAdditionalService {

    @Autowired
    private ClientDao clientDao;
    @Autowired
    private ClientServiceHelper clientServiceHelper;


}
