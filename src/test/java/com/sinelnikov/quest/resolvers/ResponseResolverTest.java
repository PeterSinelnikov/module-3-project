package com.sinelnikov.quest.resolvers;

import com.sinelnikov.quest.treeNode.TreeNode;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class ResponseResolverTest {
    ResponseResolver resolver;
    TreeNode node;

    @Test
    void resolve_passedNodeWithNoChildren_shouldReturnValidString() {
        node = Mockito.mock(TreeNode.class);
        resolver = new ResponseResolver(node);
        assertEquals("/quest.jsp",resolver.resolve());
    }

    @Test
    void resolve_passedNodeWithChildren_shouldReturnValidString() {
        node = Mockito.mock(TreeNode.class);
        Mockito.when(node.hasNoChildren()).then(invocationOnMock -> true);
        resolver = new ResponseResolver(node);
        assertEquals("/gameOver.jsp",resolver.resolve());
    }
}