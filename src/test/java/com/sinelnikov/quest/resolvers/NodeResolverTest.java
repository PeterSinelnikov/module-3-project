package com.sinelnikov.quest.resolvers;

import com.sinelnikov.quest.treeNode.TreeNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class NodeResolverTest {

    TreeNode node;
    NodeResolver resolver;

    @Test
    void resolve_passedYes_shouldInvokeGetYesNode() {
        node = Mockito.mock(TreeNode.class);
        resolver = new NodeResolver(node);
        resolver.resolve("yes");
        Mockito.verify(node).getYesNode();
    }

    @Test
    void resolve_passedNo_shouldInvokeGetNoNode() {
        node = Mockito.mock(TreeNode.class);
        resolver = new NodeResolver(node);
        resolver.resolve("no");
        Mockito.verify(node).getNoNode();
    }

    @Test
    void resolve_passedInvalidParameter_shouldThrowException() {
        node = Mockito.mock(TreeNode.class);
        resolver = new NodeResolver(node);
        assertThrows(IllegalArgumentException.class, () -> resolver.resolve(""));
    }
}