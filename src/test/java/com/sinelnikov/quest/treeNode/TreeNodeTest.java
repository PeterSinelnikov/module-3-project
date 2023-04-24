package com.sinelnikov.quest.treeNode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class TreeNodeTest {

    TreeNode node;
    String validValue = "value";
    String validWelcomeText = "welcome text";

    @Test
    void constructor_nullValue_shouldThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new TreeNode(validWelcomeText, null));
        assertEquals("TreeNode value cannot be null",exception.getMessage());
    }

    @Test
    void hasNoChildren_passedNoChildNodes_shouldReturnTrue() {
        node = new TreeNode(validWelcomeText,validValue);
        assertTrue(node.hasNoChildren());
    }

    @Test
    void hasNoChildren_passedYesNode_shouldReturnFalse() {
        node = new TreeNode(validWelcomeText,validValue);
        node.setYesNode(new TreeNode(validWelcomeText,validValue));
        assertFalse(node.hasNoChildren());
    }

    @Test
    void hasNoChildren_passedBothNodes_shouldReturnFalse() {
        node = new TreeNode(validWelcomeText,validValue);
        node.setYesNode(new TreeNode(validWelcomeText,validValue));
        node.setNoNode(new TreeNode(validWelcomeText,validValue));
        assertFalse(node.hasNoChildren());
    }

    @Test
    void getWelcomeText_shouldReturnWelcomeText() {
        node = new TreeNode(validWelcomeText,validValue);
        assertEquals(validWelcomeText,node.getWelcomeText());
    }

    @Test
    void getValue_shouldReturnValueText() {
        node = new TreeNode(validWelcomeText,validValue);
        assertEquals(validValue,node.getValue());
    }

    @Test
    void getYesNode_shouldReturnValidNode() {
        node = new TreeNode(validWelcomeText,validValue);
        TreeNode expectedNode = new TreeNode(validWelcomeText,validValue);
        node.setYesNode(expectedNode);
        assertEquals(expectedNode,node.getYesNode());
    }

    @Test
    void getNoNode_shouldReturnValidNode() {
        node = new TreeNode(validWelcomeText,validValue);
        TreeNode expectedNode = new TreeNode(validWelcomeText,validValue);
        node.setNoNode(expectedNode);
        assertEquals(expectedNode,node.getNoNode());
    }

    @Test
    void setYesNode_shouldSetPassedNode() {
        node = new TreeNode(validWelcomeText,validValue);
        TreeNode expectedNode = new TreeNode(validWelcomeText,validValue);
        node.setYesNode(expectedNode);
        assertEquals(expectedNode,node.getYesNode());
    }

    @Test
    void setNoNode_shouldSetPassedNode() {
        node = new TreeNode(validWelcomeText,validValue);
        TreeNode expectedNode = new TreeNode(validWelcomeText,validValue);
        node.setNoNode(expectedNode);
        assertEquals(expectedNode,node.getNoNode());
    }
}