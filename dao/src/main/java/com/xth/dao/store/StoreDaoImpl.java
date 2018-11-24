package com.xth.dao.store;

import com.xth.dao.BaseNoHistoryDaoImpl;
import com.xth.mapper.Mapper;
import com.xth.mapper.store.StoreMapper;
import com.xth.model.bo.store.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 * 系统生成Dao实现类
 *
 * @author admin
 * @date 2018/09/16
 */
@Repository
public class StoreDaoImpl extends BaseNoHistoryDaoImpl<Store> implements StoreDao {

    @Autowired
    private StoreMapper mapper;

    @Override
    protected Mapper<Store> getMapper() {
        return mapper;
    }


}
