package com.xth.service.company.export;

import com.xth.dao.company.CompanyDao;
import com.xth.model.so.company.CompanySo;
import com.xth.service.BaseExport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 系统生成导出类
 *
 * @author admin
 * @date 2018/09/12
 */
@Service
public class ExportCompany extends BaseExport<CompanySo> {

    private static final String TEMPLATE = "T_COMPANY.xml";
    private static final String STORE_NAME = "T_COMPANY";

    @Autowired
    private CompanyDao companyDao;


}
