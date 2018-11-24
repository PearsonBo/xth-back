package com.xth.dao.company;

import com.xth.dao.BaseNoHistoryDaoImpl;
import com.xth.mapper.Mapper;
import com.xth.mapper.company.CompanyMapper;
import com.xth.model.bo.company.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 * 系统生成Dao实现类
 * @author admin
 * @date 2018/09/12
 */
@Repository
public class CompanyDaoImpl extends BaseNoHistoryDaoImpl<Company> implements CompanyDao {

    @Autowired
    private CompanyMapper mapper;

    @Override
    protected Mapper<Company> getMapper() {
        return mapper;
    }



}
