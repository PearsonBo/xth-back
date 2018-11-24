package com.xth.service;

import com.google.common.collect.Lists;
import com.xth.helper.MenuItemServiceHelper;
import com.xth.mapper.operator.OperatorMapper;
import com.xth.model.enums.MenuTypeEnum;
import com.xth.model.tree.TreeNode;
import com.xth.model.vo.menu.MenuItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Hu Jianbo
 * @date: 2018/7/26
 */
@Service
public class MenuItemServiceImpl implements MenuItemService {

    @Autowired
    OperatorMapper operatorMapper;

    @Autowired
    MenuItemServiceHelper menuItemServiceHelper;

    @Override
    public TreeNode menuItemTree() {

        List<MenuItemVo> menuItemList = operatorMapper.selectAllMenuItem();

        return menuItemServiceHelper.buildTree(menuItemList);
    }

    @Override
    public TreeNode getMenuTreeByOperatorId(Long operatorId) {
        List<MenuItemVo> menuItemList = operatorMapper.selectMenuItemByOperatorId(operatorId);

        List<MenuItemVo> allMenu = operatorMapper.selectAllMenuItem();

        List<MenuItemVo> userMenu = joinMenuOfUser(allMenu, menuItemList);

        TreeNode menuTree = menuItemServiceHelper.buildTree(userMenu);

        geneShowTreeNode(menuTree);

        return menuTree;
    }

    /**
     * @param menuTree
     */
    private void geneShowTreeNode(TreeNode menuTree) {
        if (menuTree == null) {
            return;
        }

        // module，比如风控管理
        List<TreeNode> moduleList = menuTree.getChildren();

        for (TreeNode module : moduleList) {
            List<TreeNode> menuList = module.getChildren();
            if (menuList == null) {
                continue;
            }

            for (TreeNode menu : menuList) {
                List<TreeNode> buttonList = menu.getChildren();
                if (buttonList == null) {
                    continue;
                }
                if (menu.getData() != null && menu.getData() instanceof MenuItemVo) {
                    MenuItemVo menuItemVo = (MenuItemVo) menu.getData();
                    menuItemVo.setPermissionList(buttonList.stream().map(item -> ((MenuItemVo) (item.getData())).getPermission()).collect(Collectors.toList()));
                }
                menu.setChildren(null);
            }
        }
    }

    /**
     * 其实就是添加符合类型的Menu
     *
     * @param allMenu
     * @param menuItemList
     * @return
     */
    private List<MenuItemVo> joinMenuOfUser(List<MenuItemVo> allMenu, List<MenuItemVo> menuItemList) {
        Set<MenuItemVo> userMenuItemSet = new HashSet<>();
        Queue<MenuItemVo> selectedMenuItemQueue = new LinkedList<>(menuItemList);

        Map<Long, MenuItemVo> allMenusMap = new HashMap<>();

        for (MenuItemVo menu : allMenu) {
            allMenusMap.put(menu.getId(), menu);
        }

        while (!selectedMenuItemQueue.isEmpty()) {
            MenuItemVo elm = selectedMenuItemQueue.poll();

            if (MenuTypeEnum.menuTreeTypes().contains(elm.getType())) {
                userMenuItemSet.add(elm);
            }

            MenuItemVo parent = allMenusMap.get(elm.getParentId());

            if (parent != null && !selectedMenuItemQueue.contains(parent)) {
                selectedMenuItemQueue.offer(parent);
            }
        }
        return new ArrayList<>(userMenuItemSet);
    }

}
