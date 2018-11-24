package com.xth.model.tree;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Hu Jianbo
 * @date: 2018/7/26
 */
@Data
public class TreeNode implements Serializable, Comparable<TreeNode> {
    private static final long serialVersionUID = -875211491992629152L;

    private List<TreeNode> children;

    private String key;

    private String value;

    private Long sortIndex;

    private String parentKey;

    private Boolean isLeaf = false;

    private Object data;

    public TreeNode() {
    }

    public TreeNode(String key, String value, String parentKey, Long sortIndex, Object data) {
        this.key = key;
        this.value = value;
        this.parentKey = parentKey;
        this.data = data;
        this.sortIndex = sortIndex;
    }

    /**
     * 添加子节点
     *
     * @param child
     * @return
     */
    public boolean addChild(TreeNode child) {
        if (children == null) {
            children = new ArrayList<TreeNode>();
        }
        return children.add(child);
    }

    @Override
    public int compareTo(TreeNode o) {
        if (getSortIndex() == null && o.getSortIndex() == null) {
            return 0;
        }

        if (getSortIndex() == null) {
            return -1;
        }

        if (o.getSortIndex() == null) {
            return 1;
        }
        return (int) (getSortIndex() - o.getSortIndex());
    }

}