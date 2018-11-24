package com.xth.helper;

import com.xth.model.tree.TreeNode;
import com.xth.model.vo.menu.MenuItemVo;
import com.xth.util.TreeUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统生成Service帮助类
 *
 * @author admin
 * @date 2017/12/28
 */
@Service
public class MenuItemServiceHelper {

    protected final Logger log = LoggerFactory.getLogger(MenuItemServiceHelper.class);

    public TreeNode buildTree(List<MenuItemVo> menuItemList) {
        if (CollectionUtils.isEmpty(menuItemList)) {
            return null;
        }

        MenuItemVo rootMenuItemVo = null;
        for (MenuItemVo menuItemVo : menuItemList) {
            if (menuItemVo.getParentId() == null) {
                rootMenuItemVo = menuItemVo;
                menuItemList.remove(menuItemVo);
                break;
            }
        }

        if (rootMenuItemVo == null) {
            log.warn("menuTree is fault, have no root node");
            return null;
        }

        TreeNode rootTreeNode = menuItemToTreeNode(rootMenuItemVo);

        List<TreeNode> treeNodeList = new ArrayList<>();
        for (MenuItemVo menuItemVo : menuItemList) {
            treeNodeList.add(menuItemToTreeNode(menuItemVo));
        }

        return buildTreeFromTreeNodeList(rootTreeNode, treeNodeList);
    }

    private TreeNode buildTreeFromTreeNodeList(TreeNode rootTreeNode, List<TreeNode> treeNodeList) {
        if (CollectionUtils.isEmpty(treeNodeList) || treeNodeList == null) {
            return rootTreeNode;
        }

        Map<String, TreeNode> treeNodesMap = new HashMap<>();
        treeNodesMap.put(rootTreeNode.getKey(), rootTreeNode);
        for (TreeNode treeNode : treeNodeList) {
            treeNodesMap.put(treeNode.getKey(), treeNode);
        }

        TreeUtil.sort(treeNodeList, treeNodesMap);
        return rootTreeNode;
    }

    private TreeNode menuItemToTreeNode(MenuItemVo menuItemVo) {
        return new TreeNode(menuItemVo.getId().toString(), menuItemVo.getName(),
                menuItemVo.getParentId() == null ? null : menuItemVo.getParentId().toString(),
                menuItemVo.getSortIndex(), menuItemVo);
    }

}
