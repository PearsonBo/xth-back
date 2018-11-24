package com.xth.dao.operator;


import com.xth.dao.BaseDao;
import com.xth.model.bo.operator.Operator;
import com.xth.model.vo.menu.MenuItemVo;
import com.xth.model.vo.menu.RoleVo;

import java.util.List;

/**
 * 系统生成Dao
 *
 * @author admin
 * @date 2017/11/14
 */
public interface OperatorDao extends BaseDao<Operator> {


    /**
     * 根据操作人id查找权限列表
     *
     * @param operatorId
     * @return
     */
    List<MenuItemVo> selectMenuItemByOperatorId(Long operatorId);

    /**
     * 根据操作人id查找角色列表
     *
     * @param operatorId
     * @return
     */
    List<RoleVo> selectRoleListByOperatorId(Long operatorId);
}
