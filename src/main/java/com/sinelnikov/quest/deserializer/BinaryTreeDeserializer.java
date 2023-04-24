package com.sinelnikov.quest.deserializer;

import com.sinelnikov.quest.treeNode.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreeDeserializer {

    static final String LINE_BREAK = "\n";
    static final String NULL_NODE_SIGN = "#";
    static final String DELIMITER = ";";


    public TreeNode deserialize(String data) {
        if (data == null || data.isBlank()) {
            throw new IllegalArgumentException("no data for deserializing");
        }
        List<String> lines = new LinkedList<>(Arrays.asList(data.split(LINE_BREAK)));
        return buildTree(lines);
    }

    private TreeNode buildTree(List<String> lines) {
        String value = lines.remove(0);
        if (value.contains(NULL_NODE_SIGN)) {
            return null;
        }
        String[] constructorParameters = value.split(DELIMITER);
        if (constructorParameters.length != 2) {
            throw new IllegalArgumentException("Delimiter " + DELIMITER
                    + " not found. Game tree configuration file is invalid");
        }
        TreeNode root = new TreeNode(constructorParameters[0], constructorParameters[1]);
        root.setYesNode(buildTree(lines));
        root.setNoNode(buildTree(lines));
        return root;
    }
}
