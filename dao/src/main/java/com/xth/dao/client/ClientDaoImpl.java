package com.xth.dao.client;

import com.xth.dao.BaseNoHistoryDaoImpl;
import com.xth.mapper.Mapper;
import com.xth.mapper.client.ClientMapper;
import com.xth.model.bo.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 * 系统生成Dao实现类
 *
 * @author admin
 * @date 2018/09/18
 */
@Repository
public class ClientDaoImpl extends BaseNoHistoryDaoImpl<Client> implements ClientDao {

    @Autowired
    private ClientMapper mapper;

    @Override
    protected Mapper<Client> getMapper() {
        return mapper;
    }

}
