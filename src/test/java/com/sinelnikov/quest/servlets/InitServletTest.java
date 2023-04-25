package com.sinelnikov.quest.servlets;

import com.sinelnikov.quest.deserializer.BinaryTreeDeserializer;
import com.sinelnikov.quest.deserializer.BinaryTreeReader;
import com.sinelnikov.quest.treeNode.TreeNode;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static com.sinelnikov.quest.resolvers.ResponseResolver.QUEST_JSP;
import static com.sinelnikov.quest.servlets.InitServlet.PATH_TO_GAME_TREE;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InitServletTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private ServletContext servletContext;
    @Mock
    private RequestDispatcher requestDispatcher;
    private InitServlet initServlet;
    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        initServlet = new InitServlet();
        when(request.getSession(true)).thenReturn(session);
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getRequestDispatcher(QUEST_JSP)).thenReturn(requestDispatcher);
    }

    @Test
    void doGet_shouldAssignNodeAttributeAndForward() throws ServletException, IOException {
        String data = "data";
        TreeNode root = mock(TreeNode.class);
        try (MockedConstruction<BinaryTreeReader> reader = mockConstruction(BinaryTreeReader.class,
                (mock, context) -> when(mock.read(PATH_TO_GAME_TREE)).thenReturn(data));
             MockedConstruction<BinaryTreeDeserializer> deserializer = mockConstruction(BinaryTreeDeserializer.class,
                     (mock, context) -> when(mock.deserialize(data)).thenReturn(root))) {
            initServlet.doGet(request,response);
            verify(session).setAttribute("node",root);
            verify(requestDispatcher).forward(request,response);
        }
    }

    @AfterEach
    void releaseMocks() throws Exception {
        autoCloseable.close();
    }
}