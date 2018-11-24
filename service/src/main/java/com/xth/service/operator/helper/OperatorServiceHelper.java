package com.xth.service.operator.helper;

import com.xth.dao.operator.OperatorDao;
import com.xth.model.constant.CommonConstant;
import com.xth.model.enums.MenuTypeEnum;
import com.xth.model.exception.BizException;
import com.xth.model.exception.ExceptionType;
import com.xth.model.so.operator.OperatorSo;
import com.xth.model.vo.menu.MenuItemVo;
import com.xth.model.vo.menu.RoleVo;
import com.xth.model.vo.operator.OperatorVo;
import com.xth.service.AbstractNoTransactionalService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 系统生成Service帮助类
 *
 * @author admin
 * @date 2018/10/08
 */
@Service
public class OperatorServiceHelper extends AbstractNoTransactionalService {

    @Autowired
    private OperatorDao operatorDao;

    public OperatorVo findVoByLoginName(String loginName) {
        OperatorSo operatorSo = new OperatorSo();
        operatorSo.setLoginName(loginName);
        List<OperatorVo> operatorVos = operatorDao.listVoBySo(operatorSo);
        if (CollectionUtils.isEmpty(operatorVos) || operatorVos.size() > 1) {
            throw new BizException(String.format("登录名【%s】不存在"), ExceptionType.VIOLATE_BIZ_RULE);
        }
        if (operatorVos.size() > 1) {
            throw new BizException(String.format("登录名【%s】存在多个"), ExceptionType.DIRTY_DATA);
        }

        return operatorVos.get(0);
    }

    /**
     * 权限信息
     */
    public Set<String> listPermissionList(Long operatorId) {
        List<MenuItemVo> menuItemList = operatorDao.selectMenuItemByOperatorId(operatorId);

        Set<String> permissionNameSet = menuItemList.stream()
                .filter(menuItem -> menuItem.getType().equals(MenuTypeEnum.PERMISSION) && StringUtils.isNotEmpty(menuItem.getUrl()) && StringUtils.isNotEmpty(menuItem.getPermission()))
                .map(item -> (item.getUrl() + ":" + item.getPermission()))
                .collect(Collectors.toSet());

        Set<String> menuNameSet = menuItemList.stream()
                .filter(menuItem -> menuItem.getType().equals(MenuTypeEnum.MENU) && StringUtils.isNotEmpty(menuItem.getUrl()))
                .map(MenuItemVo::getUrl)
                .collect(Collectors.toSet());

        Set<String> nameSet = new HashSet<>();
        nameSet.addAll(permissionNameSet);
        nameSet.addAll(menuNameSet);
        return nameSet;
    }

    /**
     * 角色信息
     */
    public Set<String> listRoleList(Long operatorId) {
        List<RoleVo> roleList = operatorDao.selectRoleListByOperatorId(operatorId);
        return roleList.stream().map(role -> role.getId().toString()).collect(
                Collectors.toSet());
    }
}
