package com.sinelnikov.quest.servlets;

import com.sinelnikov.quest.resolvers.NodeResolver;
import com.sinelnikov.quest.resolvers.ResponseResolver;
import com.sinelnikov.quest.treeNode.TreeNode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QuestLogicServletTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    private QuestLogicServlet servlet;
    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        servlet = new QuestLogicServlet();
        when(request.getSession()).thenReturn(session);
    }

    @Test
    void doGet_passedInvalidAttribute_shouldThrowException() {
        Object node = new Object();
        when(session.getAttribute("node")).thenReturn(node);
        IllegalStateException exception = assertThrows(IllegalStateException.class, () ->
                servlet.doGet(request, response));
        assertEquals("Invalid node attribute", exception.getMessage());
    }

    @Test
    void doGet_passedValidAttribute_shouldSetAttribute() throws IOException {
        Object node = mock(TreeNode.class);
        TreeNode selectedNode = mock(TreeNode.class);
        when(session.getAttribute("node")).thenReturn(node);
        when(request.getParameter("option")).thenReturn("data");
        try (MockedConstruction<NodeResolver> nodeResolver = mockConstruction(NodeResolver.class,
                (mock, context) -> when(mock.resolve("data")).thenReturn(selectedNode));
             MockedConstruction<ResponseResolver> responseRes = mockConstruction(ResponseResolver.class,
                     (mock, context) -> when(mock.resolve()).thenReturn("redirect"))) {
            servlet.doGet(request, response);
            verify(session).setAttribute("node", selectedNode);
            verify(response).sendRedirect("redirect");
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {""," "})
    void doGet_passedInvalidParameter_shouldThrowException(String parameter) {
        Object node = mock(TreeNode.class);
        when(session.getAttribute("node")).thenReturn(node);
        when(request.getParameter("option")).thenReturn(parameter);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                servlet.doGet(request, response));
        assertEquals("Invalid request parameter: 'option'", exception.getMessage());
    }

    @AfterEach
    void releaseMocks() throws Exception {
        autoCloseable.close();
    }
}