package com.xth.service.operator.common;


import com.xth.model.base.PageList;
import com.xth.model.so.operator.OperatorSo;
import com.xth.model.vo.operator.OperatorVo;

/**
 * 系统生成Service类
 *
 * @author admin
 * @date 2018/10/08
 */
public interface OperatorService {

    /**
     * 新增
     *
     * @param operator 新增对象
     * @return 新增对象的id
     */
    Long create(OperatorVo operator);

    /**
     * 删除
     *
     * @param id 删除对象的id
     */
    void delete(Long id);

    /**
     * 修改
     *
     * @param operator 修改对象
     */
    void update(OperatorVo operator);

    /**
     * 单个查询
     *
     * @param id 查询对象id
     * @return 查询结果
     */
    OperatorVo find(Long id);

    /**
     * 按条件查询
     *
     * @param operatorSo 查询条件
     * @return 查询结果
     */
    PageList<OperatorVo> listPagination(OperatorSo operatorSo);


}

