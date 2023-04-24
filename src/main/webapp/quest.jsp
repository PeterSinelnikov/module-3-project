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
    <script src="static/jquery-3.6.0.js"></script>
    <script>
        $(document).on('click', '#firstOption', function () {
            let submitBtn = $('<button value="submit">Отправить</button>');
            submitBtn.click(function () {
                window.location='/game?option=yes';
            });
            $("#submitButton").empty().append(submitBtn);
        })
        $(document).on('click', '#secondOption', function () {
            let submitBtn = $('<button value="submit">Отправить</button>');
            submitBtn.click(function () {
                window.location='/game?option=no';
            });
            $("#submitButton").empty().append(submitBtn);
        })
    </script>
</head>
<body>
<%
    TreeNode node = (TreeNode) request.getSession().getAttribute("node");
%>
<fieldset>
    <legend><%= node.getValue()%></legend>
    <div>
        <input type="radio" id="firstOption" name="drone" value="yes">
        <label for="firstOption"><%= node.getYesNode().getWelcomeText()%></label>
        <br>
        <input type="radio" id="secondOption" name="drone" value="no">
        <label for="secondOption"><%= node.getNoNode().getWelcomeText()%></label>
    </div>
    <br>
    <div id="submitButton"></div>
</fieldset>
</body>
</html>
