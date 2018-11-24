package com.xth.service.company.common;

import com.xth.dao.company.CompanyDao;
import com.xth.model.base.PageList;
import com.xth.model.bo.company.Company;
import com.xth.model.so.company.CompanySo;
import com.xth.model.vo.company.CompanyVo;
import com.xth.service.AbstractTransactionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


/**
 * 系统生成Service实现类
 * @author admin
 * @date 2018/09/12
 */
@Service
public class CompanyServiceImpl extends AbstractTransactionalService implements CompanyService {

    @Autowired
    private CompanyDao companyDao;

    /**
     * 新增
     *
     * @param company 新增对象
     * @return 新增对象的id
     */
    @Override
    public Long create(CompanyVo company) {
        checkBeforeCreate(company);
        Company companyBo = dozer.convert(company, Company.class);
        return companyDao.insert(companyBo);
    }

    private void checkBeforeCreate(CompanyVo company){
        checkRequired(company);
        checkDuplicate4Create(company);
    }


    /**
     * 删除
     *
     * @param id 删除对象的id
     */
    @Override
    public void delete(Long id) {
        companyDao.delete(id);
    }

    /**
     * 修改
     *
     * @param company 修改对象
     */
    @Override
    public void update(CompanyVo company) {
        checkBeforeUpdate(company);
        Company companyBo = dozer.convert(company, Company.class);
        companyDao.update(companyBo);
    }

    private void checkBeforeUpdate(CompanyVo company){
        checkRequired(company);
        checkDuplicate4Update(company);
    }

    /**
     * 单个查询
     *
     * @param id 查询对象id
     * @return 查询结果
     */
    @Override
    public CompanyVo find(Long id) {
        return companyDao.findVo(id);
    }

    /**
     * 按条件查询
     *
     * @param companySo 查询条件
     * @return 查询结果
     */
    @Override
    public PageList<CompanyVo> listPagination(CompanySo companySo) {
        List<CompanyVo> list = companyDao.listPaginationVoBySo(companySo);
        int count = companyDao.countBySo(companySo);
        return new PageList<>(list, count);
    }

    /**
     * 检查必填字段
     */
    private void checkRequired(CompanyVo company) {

    }

    /**
     * 检查字段唯一性（创建）
     */
    private void checkDuplicate4Create(CompanyVo company) {

    }



    /**
     * 检查字段唯一性（更新）
     */
    private void checkDuplicate4Update(CompanyVo company) {

    }





}
