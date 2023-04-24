<%@ page import="com.sinelnikov.quest.treeNode.TreeNode" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Game Over</title>
</head>
<body>
<%
    TreeNode node = (TreeNode) request.getSession().getAttribute("node");
%>
<h2><%= node.getValue()%></h2>
<br>
<button id="restart" value="restart" onclick="window.location='/start'">Начать заново</button>
</body>
</html>
