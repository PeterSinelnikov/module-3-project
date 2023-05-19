package com.sinelnikov.quest.treeNode;

import lombok.Getter;
import lombok.Setter;

@Getter
public class TreeNode {
    private final String welcomeText;
    private final String value;
    @Setter
    private TreeNode yesNode;
    @Setter
    private TreeNode noNode;

    public TreeNode(String welcomeText, String value) {
        if (value == null) {
            throw new IllegalArgumentException("TreeNode value cannot be null");
        }
        this.welcomeText = welcomeText;
        this.value = value;
    }

    public boolean isLeaf() {
        return this.yesNode == null && this.noNode == null;
    }

}
