package com.sinelnikov.quest.resolvers;

import com.sinelnikov.quest.treeNode.TreeNode;

public class ResponseResolver {

    public static final String QUEST_JSP = "/quest.jsp";
    public static final String GAME_OVER_JSP = "/gameOver.jsp";


    private final TreeNode node;

    public ResponseResolver(TreeNode node) {
        this.node = node;
    }

    public String resolve() {
        if (node.isLeaf()) {
            return GAME_OVER_JSP;
        } else {
            return QUEST_JSP;
        }
    }
}
