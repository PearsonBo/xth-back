package com.xth.util;

import com.xth.model.tree.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Hu Jianbo
 * @date: 2018/7/28
 */
public class TreeUtil {

    private static final Logger log = LoggerFactory.getLogger(TreeUtil.class);

    public static void sort(List<TreeNode> treeNodeList, Map<String, TreeNode> treeNodesMap) {
        for (TreeNode treeNode : treeNodeList) {
            String parentKey = treeNode.getParentKey();

            if (parentKey == null) {
                log.warn("treenode has no parentKey - key: " + treeNode.getKey());
                continue;
            }

            TreeNode parent = treeNodesMap.get(parentKey);
            if (parent == null) {
                log.warn("treenode has no parent treeNode - key: " + treeNode.getKey());
                continue;
            }
            parent.addChild(treeNode);
            Collections.sort(parent.getChildren());

        }
    }
}
