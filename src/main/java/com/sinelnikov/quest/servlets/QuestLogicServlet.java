package com.sinelnikov.quest.servlets;

import com.sinelnikov.quest.resolvers.NodeResolver;
import com.sinelnikov.quest.resolvers.ResponseResolver;
import com.sinelnikov.quest.treeNode.TreeNode;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "Quest Logic Servlet", value = "/game")
public class QuestLogicServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        TreeNode node = getNodeAttribute(session);
        String selectedOption = getSelectedOption(req);
        TreeNode selectedNode = new NodeResolver(node).resolve(selectedOption);
        session.setAttribute("node", selectedNode);
        String redirect = new ResponseResolver(selectedNode).resolve();
        resp.sendRedirect(redirect);
    }

    private TreeNode getNodeAttribute(HttpSession session) {
        Object node = session.getAttribute("node");
        if (TreeNode.class != node.getClass()) {
            session.invalidate();
            throw new IllegalStateException("Invalid node attribute");
        }
        return (TreeNode) node;
    }

    private String getSelectedOption(HttpServletRequest request) {
        String option = request.getParameter("option");
        if (!option.isBlank()) {
            return option;
        } else {
            throw new IllegalArgumentException("Invalid request parameter: 'option'");
        }
    }
}
