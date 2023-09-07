<%--
  Created by IntelliJ IDEA.
  User: ART PRONKIN
  Date: 07.09.2023
  Time: 18:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/start" method="GET">

    Первый игрок: <input type="text" name="player1">
    <br />
    Второй игрок: <input type="text" name="player2" />
    <br />
    <input type="submit" value="Старт" />

</form>


</body>
</html>
