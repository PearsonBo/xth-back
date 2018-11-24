package com.xth.service.company.common;


import com.xth.model.base.PageList;
import com.xth.model.so.company.CompanySo;
import com.xth.model.vo.company.CompanyVo;

/**
 * 系统生成Service类
 * @author admin
 * @date 2018/09/12
 */
public interface CompanyService {

    /**
     * 新增
     * @param company 新增对象
     * @return 新增对象的id
     */
    Long create(CompanyVo company);

    /**
     * 删除
     * @param id 删除对象的id
     */
    void delete(Long id);

    /**
     * 修改
     * @param company 修改对象
     */
    void update(CompanyVo company);

    /**
     * 单个查询
     * @param id 查询对象id
     * @return 查询结果
     */
    CompanyVo find(Long id);

    /**
     * 按条件查询
     * @param companySo 查询条件
     * @return 查询结果
     */
    PageList<CompanyVo> listPagination(CompanySo companySo);



}

