package com.xth.dao.rebate;

import com.xth.dao.BaseNoHistoryDaoImpl;
import com.xth.mapper.Mapper;
import com.xth.mapper.rebate.RebateMapper;
import com.xth.model.bo.rebate.Rebate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 * 系统生成Dao实现类
 *
 * @author admin
 * @date 2018/09/29
 */
@Repository
public class RebateDaoImpl extends BaseNoHistoryDaoImpl<Rebate> implements RebateDao {

    @Autowired
    private RebateMapper mapper;

    @Override
    protected Mapper<Rebate> getMapper() {
        return mapper;
    }


}
