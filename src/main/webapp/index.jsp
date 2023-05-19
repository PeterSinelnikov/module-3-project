<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Quest Game</title>
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
    Integer counter = 0;
    session.setAttribute("counter",counter);
%>
<h2>Prologue</h2>
<p>
    Welcome to the ultimate test of survival skills, where your every decision determines whether you live or die.
    In this quest-game, you will find yourself stranded in the middle of a dense forest after surviving a plane crash.
    You are alone and vulnerable, with nothing but your wits to keep you alive.
</p>
<p>
    You must navigate a series of binary choices, each with its own set of risks and rewards.
    Will you make the right decisions and emerge victorious, or will you succumb to the harsh realities of the wilderness?
</p>
<p>
    As you set out on your journey, remember that every step counts.
    Your survival depends on your ability to think clearly, act decisively, and remain focused in the face of danger.
</p>
<p>
    Are you ready to put your survival skills to the test? Let's begin.
</p>
<br>
<div>
    <button id="playerName" value="submit" onclick="window.location='/start'">Поехали!</button>
</div>
</body>
</html>
