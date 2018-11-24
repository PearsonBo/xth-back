package com.xth.service.company.additional;

import com.xth.dao.company.CompanyDao;
import com.xth.service.AbstractTransactionalService;
import com.xth.service.company.helper.CompanyServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 系统生成AdditionalServiceImpl
 * @author admin
 * @date 2018/09/12
 */
@Service
public class CompanyAdditionalServiceImpl extends AbstractTransactionalService implements CompanyAdditionalService {

    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private CompanyServiceHelper companyServiceHelper;



}
