<%@ page import="com.sinelnikov.quest.treeNode.TreeNode" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Game Over</title>
    <style>
        h2 {
            word-wrap: normal;
            font-size: 17pt;
            width: 700px;
        }
    </style>
</head>
<body>
<%
    TreeNode node = (TreeNode) request.getSession().getAttribute("node");
%>
<h2><%= node.getValue()%></h2>
<br>
<button id="restart" value="restart" onclick="window.location='/start'">Try again</button>
</body>
</html>
