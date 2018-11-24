package com.xth.service.company.helper;

import com.xth.dao.company.CompanyDao;
import com.xth.service.AbstractNoTransactionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 系统生成Service帮助类
 * @author admin
 * @date 2018/09/12
 */
@Service
public class CompanyServiceHelper extends AbstractNoTransactionalService {

    @Autowired
    private CompanyDao companyDao;



}
