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
    <script src="static/jquery-3.6.0.js"></script>
    <script>
        /*function submit() {
            let name = $('#name').val();
            $.ajax({
                url: '/start',
                type: 'POST',
                contentType: 'application/json;charset=UTF-8',
                data: name,
                async: false,
            });
        }*/
    </script>
</head>
<body>
<h2>Пролог</h2>
<p>
    Ты стоишь в космическом порту и готов подняться на борт своего корабля.
    Разве ты не об этом мечтал? Стать капитаном галакического судна с экипажем,
    который будет совершать подвиги под твоим командованием.
</p>
<br>
<h2>Знакомство с экипажем</h2>
<p>
    Когда ты поднялся на борт корабля, тебя поприветствовала девушка с черной папкой в руках:
    <br>
    - Здравствуйте, командир! Я Зинаида - ваша помощница. Видите? Там в углу пьет кофе наш штурман -
    сержант Перегарный Штейф, под штурвалом спит наш бортмеханик - Черный Богдан, а фотографирует его
    Сергей Стальная Пятка - наш навигатор.
</p>
<div>
    <%--<input id="name">--%>
    <button id="playerName" value="submit" onclick="window.location='/start'">Поехали!</button>
</div>
</body>
</html>
