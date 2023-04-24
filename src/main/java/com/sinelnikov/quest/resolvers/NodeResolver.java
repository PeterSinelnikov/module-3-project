package com.sinelnikov.quest.resolvers;

import com.sinelnikov.quest.treeNode.TreeNode;

public class NodeResolver {
    private static final String YES = "yes";
    private static final String NO = "no";
    private final TreeNode node;

    public NodeResolver(TreeNode node) {
        this.node = node;
    }

    public TreeNode resolve(String selected) {
        if (YES.equals(selected)) {
            return node.getYesNode();
        } else if (NO.equals(selected)) {
            return node.getNoNode();
        }
        throw new IllegalArgumentException("Selected option " + selected + "is invalid. Only 'yes' or 'no' are accepted.");
    }
}
