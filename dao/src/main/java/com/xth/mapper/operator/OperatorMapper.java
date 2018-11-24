package com.xth.mapper.operator;


import com.xth.mapper.Mapper;
import com.xth.model.bo.operator.Operator;
import com.xth.model.vo.menu.MenuItemVo;
import com.xth.model.vo.menu.RoleVo;

import java.util.List;

/**
 * 系统生成Mapper类
 *
 * @author admin
 * @date 2018/10/08
 */
public interface OperatorMapper extends Mapper<Operator> {


    List<MenuItemVo> listMenuItemByOperatorId(Long operatorId);

    List<RoleVo> listRoleListByOperatorId(Long operatorId);

    List<MenuItemVo> selectAllMenuItem();

    List<MenuItemVo> selectMenuItemByOperatorId(Long operatorId);
}
