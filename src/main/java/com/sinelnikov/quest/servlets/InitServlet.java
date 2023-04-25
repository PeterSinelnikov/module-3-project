package com.sinelnikov.quest.servlets;

import com.sinelnikov.quest.deserializer.BinaryTreeDeserializer;
import com.sinelnikov.quest.deserializer.BinaryTreeReader;
import com.sinelnikov.quest.treeNode.TreeNode;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import static com.sinelnikov.quest.resolvers.ResponseResolver.QUEST_JSP;

@WebServlet(name = "InitServlet", value = "/start")
public class InitServlet extends HttpServlet {
    static final String PATH_TO_GAME_TREE = "/WEB-INF/classes/gameTree.txt";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        ServletContext servletContext = req.getServletContext();
        String data = new BinaryTreeReader(servletContext).read(PATH_TO_GAME_TREE);
        TreeNode root = new BinaryTreeDeserializer().deserialize(data);
        session.setAttribute("node", root);
        servletContext.getRequestDispatcher(QUEST_JSP).forward(req,resp);
    }
}

