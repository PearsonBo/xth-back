package com.xth.dao.level;

import com.xth.dao.BaseNoHistoryDaoImpl;
import com.xth.mapper.Mapper;
import com.xth.mapper.level.LevelMapper;
import com.xth.model.bo.level.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 * 系统生成Dao实现类
 *
 * @author admin
 * @date 2018/09/24
 */
@Repository
public class LevelDaoImpl extends BaseNoHistoryDaoImpl<Level> implements LevelDao {

    @Autowired
    private LevelMapper mapper;

    @Override
    protected Mapper<Level> getMapper() {
        return mapper;
    }


}
