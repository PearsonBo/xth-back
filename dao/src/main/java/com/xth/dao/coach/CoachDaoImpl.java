package com.xth.dao.coach;

import com.xth.dao.BaseNoHistoryDaoImpl;
import com.xth.mapper.Mapper;
import com.xth.mapper.coach.CoachMapper;
import com.xth.model.bo.coach.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 * 系统生成Dao实现类
 *
 * @author admin
 * @date 2018/09/17
 */
@Repository
public class CoachDaoImpl extends BaseNoHistoryDaoImpl<Coach> implements CoachDao {

    @Autowired
    private CoachMapper mapper;

    @Override
    protected Mapper<Coach> getMapper() {
        return mapper;
    }


}
