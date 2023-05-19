package com.sinelnikov.quest.treeNode;

import lombok.Getter;
import lombok.Setter;

public class TreeNode {
    @Getter
    private final String welcomeText;
    @Getter
    private final String value;
    @Getter
    @Setter
    private TreeNode yesNode;
    @Getter
    @Setter
    private TreeNode noNode;

    public TreeNode(String welcomeText, String value) {
        if (value == null) {
            throw new IllegalArgumentException("TreeNode value cannot be null");
        }
        this.welcomeText = welcomeText;
        this.value = value;
    }

    public boolean hasNoChildren() {
        return this.yesNode ==null && this.noNode == null;
    }

}
