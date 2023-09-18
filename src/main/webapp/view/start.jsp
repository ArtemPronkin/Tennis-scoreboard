<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Начать новый матч</title>
    <style>
        h1 {
            text-align: center;
            margin-top: 15px;
        }
        form {
            text-align: center;
            margin-top: 30px;
        }

</style>
</head>
<body>
<h1>Начать новый матч</h1>
<div>
    <form action="${pageContext.request.contextPath}/new-match" method="POST">
        <label>Имя Игрока 1:
            <input type="text" name="player1" required title="Введите имя игрока"><br />
        </label>
        <label>Имя Игрока 2:
            <input type="text" name="player2" required title="Введите имя игрока"><br />
        </label>
        <p>${error_message.message()}</p>
        <button type="reset" onclick="location.href='${pageContext.request.contextPath}/'">На главную</button>
        <button type="submit">Старт</button>
    </form>
</div>



</body>
</html>
