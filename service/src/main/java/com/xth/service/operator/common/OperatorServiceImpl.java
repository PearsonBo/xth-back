package com.xth.service.operator.common;

import com.xth.dao.operator.OperatorDao;
import com.xth.model.base.PageList;
import com.xth.model.bo.operator.Operator;
import com.xth.model.so.operator.OperatorSo;
import com.xth.model.vo.operator.OperatorVo;
import com.xth.service.AbstractTransactionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


/**
 * 系统生成Service实现类
 *
 * @author admin
 * @date 2018/10/08
 */
@Service
public class OperatorServiceImpl extends AbstractTransactionalService implements OperatorService {

    @Autowired
    private OperatorDao operatorDao;

    /**
     * 新增
     *
     * @param operator 新增对象
     * @return 新增对象的id
     */
    @Override
    public Long create(OperatorVo operator) {
        checkBeforeCreate(operator);
        Operator operatorBo = dozer.convert(operator, Operator.class);
        return operatorDao.insert(operatorBo);
    }

    private void checkBeforeCreate(OperatorVo operator) {
        checkRequired(operator);
        checkDuplicate4Create(operator);
    }


    /**
     * 删除
     *
     * @param id 删除对象的id
     */
    @Override
    public void delete(Long id) {
        operatorDao.delete(id);
    }

    /**
     * 修改
     *
     * @param operator 修改对象
     */
    @Override
    public void update(OperatorVo operator) {
        checkBeforeUpdate(operator);
        Operator operatorBo = dozer.convert(operator, Operator.class);
        operatorDao.update(operatorBo);
    }

    private void checkBeforeUpdate(OperatorVo operator) {
        checkRequired(operator);
        checkDuplicate4Update(operator);
    }

    /**
     * 单个查询
     *
     * @param id 查询对象id
     * @return 查询结果
     */
    @Override
    public OperatorVo find(Long id) {
        return operatorDao.findVo(id);
    }

    /**
     * 按条件查询
     *
     * @param operatorSo 查询条件
     * @return 查询结果
     */
    @Override
    public PageList<OperatorVo> listPagination(OperatorSo operatorSo) {
        List<OperatorVo> list = operatorDao.listPaginationVoBySo(operatorSo);
        int count = operatorDao.countBySo(operatorSo);
        return new PageList<>(list, count);
    }

    /**
     * 检查必填字段
     */
    private void checkRequired(OperatorVo operator) {

    }

    /**
     * 检查字段唯一性（创建）
     */
    private void checkDuplicate4Create(OperatorVo operator) {

    }


    /**
     * 检查字段唯一性（更新）
     */
    private void checkDuplicate4Update(OperatorVo operator) {

    }


}
