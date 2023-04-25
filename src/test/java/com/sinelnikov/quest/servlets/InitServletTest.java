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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static com.sinelnikov.quest.resolvers.ResponseResolver.QUEST_JSP;
import static com.sinelnikov.quest.servlets.InitServlet.PATH_TO_GAME_TREE;
import static org.junit.jupiter.api.Assertions.*;
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
    @Mock
    private BinaryTreeDeserializer deserializer;
    private InitServlet initServlet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        initServlet = new InitServlet();
        when(request.getSession(true)).thenReturn(session);
        when(request.getServletContext()).thenReturn(servletContext);
        when(request.getRequestDispatcher(QUEST_JSP)).thenReturn(requestDispatcher);
    }

    @Test
    void doGet() throws ServletException, IOException {
        String data = "data";
        BinaryTreeReader reader = mock(BinaryTreeReader.class);
        TreeNode root = mock(TreeNode.class);
        when(reader.read(servletContext,PATH_TO_GAME_TREE)).thenReturn(data);
        when(deserializer.deserialize(data)).thenReturn(root);
        initServlet.doGet(request,response);
        verify(session).setAttribute("node",root);
        verify(servletContext).getRequestDispatcher(QUEST_JSP).forward(request,response);
    }
}