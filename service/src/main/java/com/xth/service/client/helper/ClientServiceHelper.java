package com.xth.service.client.helper;

import com.xth.dao.client.ClientDao;
import com.xth.service.AbstractNoTransactionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 系统生成Service帮助类
 *
 * @author admin
 * @date 2018/09/18
 */
@Service
public class ClientServiceHelper extends AbstractNoTransactionalService {

    @Autowired
    private ClientDao clientDao;


}
