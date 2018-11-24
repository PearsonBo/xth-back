package com.xth.service;


import com.xth.model.tree.TreeNode;

/**
 * @author Hu Jianbo
 * @date: 2018/7/26
 */
public interface MenuItemService {

    TreeNode menuItemTree();

    TreeNode getMenuTreeByOperatorId(Long operatorId);
}
