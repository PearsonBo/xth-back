package com.xth.dao.operator;

import com.xth.dao.BaseNoHistoryDaoImpl;
import com.xth.mapper.Mapper;
import com.xth.mapper.operator.OperatorMapper;
import com.xth.model.bo.operator.Operator;
import com.xth.model.vo.menu.MenuItemVo;
import com.xth.model.vo.menu.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 系统生成Dao实现类
 *
 * @author admin
 * @date 2018/10/08
 */
@Repository
public class OperatorDaoImpl extends BaseNoHistoryDaoImpl<Operator> implements OperatorDao {

    @Autowired
    private OperatorMapper mapper;

    @Override
    protected Mapper<Operator> getMapper() {
        return mapper;
    }


    @Override
    public List<MenuItemVo> selectMenuItemByOperatorId(Long operatorId) {
        return mapper.listMenuItemByOperatorId(operatorId);
    }

    @Override
    public List<RoleVo> selectRoleListByOperatorId(Long operatorId) {
        return mapper.listRoleListByOperatorId(operatorId);
    }
}
