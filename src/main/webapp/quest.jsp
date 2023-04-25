<%@ page import="com.sinelnikov.quest.treeNode.TreeNode" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Quest</title>
    <style>
        P {
            word-wrap: normal;
            font-size: 14pt;
            width: 700px;
        }
    </style>
</head>
<body>
<%
    TreeNode node = (TreeNode) session.getAttribute("node");
    Integer counter = (Integer) session.getAttribute("counter");
%>

<br>
<form action="/game" method="get">
    <p><%= node.getValue()%></p>
    <input type="radio" id="param1" name="option" value="yes">
    <label for="param1"><%= node.getYesNode().getWelcomeText()%></label><br>
    <input type="radio" id="param2" name="option" value="no">
    <label for="param2"><%= node.getNoNode().getWelcomeText()%></label><br>
    <br>
    <input type="submit" value="Make choice">
</form>
<br><br>
<h4>Number of attempts: <%= counter%></h4>

</body>
</html>
