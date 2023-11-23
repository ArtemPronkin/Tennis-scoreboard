<%@ page import="app.serviceMatch.matchesScoreCalculations.SetScore" %>
<%@ page import="java.util.List" %>
<%@ page import="app.serviceMatch.matchesScoreCalculations.MatchScore" %>
<%@ page import="app.serviceMatch.matchesScoreCalculations.Score" %>
<%@ page import="app.serviceMatch.matchesScoreCalculations.State" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Текущий матч</title>
    <style> table {
        width: 600px;
        border: 1px solid green;
        margin: auto;
    }

    td {
        text-align: center;
    }

    h3 {
        text-align: center;
        margin-top: 15px;
    }

    form {
        text-align: center;
    }
    </style>
</head>
<body>

<%
    var match = (MatchScore) request.getAttribute("match");
    var list = match.getSetList();
    var curSet = match.getCurrentNumberSet();
    Score game = match.getGameScore();
    if (list.get(curSet).getState().equals(State.TIE_BREAK)) {
        game = match.getTieBreak();
    }

%>
<h3>Код матча ${uuid}</h3>
<% if (match.getState().equals(State.ONGOING)) {
    out.print("<h3>Текущий сет: " + (curSet + 1) + "</h3>");
    if (list.get(curSet).getState().equals(State.TIE_BREAK)) {
        out.print("<h3>Обьявлен Тай-Брейк</h3>");
    } else out.print("<h3>Идет игра</h3>");
} else {
    out.print("<h3>Матч завершен</h3>");
    if (match.getState().equals(State.PLAYER_ONE_WON)) {
        out.print("<h3>Выиграл игрок : " + request.getAttribute("player2") + " </h3>");
    } else out.print("<h3>Выиграл игрок : " + request.getAttribute("player1") + " </h3>");

}%>


<table>
    <thead>
    <tr>
        <th>Имя игрока</th>
        <th>Счет матча</th>
        <th>Сет 1</th>
        <th>Сет 2</th>
        <th>Сет 3</th>
        <th>Текущая игра</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>${player1}</td>
        <td><%= match.getPlayerScore(0)%>
        </td>
        <td><%= list.get(0).getPlayerScore(0)%>
        </td>
        <td><%= list.get(1).getPlayerScore(0)%>
        </td>
        <td><%= list.get(2).getPlayerScore(0)%>
        </td>
        <td><%= game.getPlayerScore(0).toString()%>
        </td>

    </tr>
    <tr>
        <td>${player2}</td>
        <td><%= match.getPlayerScore(1)%>
        </td>
        <td><%= list.get(0).getPlayerScore(1)%>
        </td>
        <td><%= list.get(1).getPlayerScore(1)%>
        </td>
        <td><%= list.get(2).getPlayerScore(1)%>
        </td>
        <td><%= game.getPlayerScore(1).toString()%>
        </td>
    </tr>
    </tbody>

</table>
<form action="${pageContext.request.contextPath}/match-score?uuid=${uuid}" method="POST">
    <%
        if (match.getState().equals(State.ONGOING)) {
            out.print("<p><button name=\"win\" value=\"player1\" type=\"submit\">Игрок 1 выиграл текущее очко</button></p>");
            out.print("<p><button name=\"win\" value=\"player2\" type=\"submit\">Игрок 2 выиграл текущее очко</button></p>");
        } else {
            out.print("<p><button name=\"win\" value=\"end\" type=\"submit\">Сохранить матч и выйти</button></p>");
        }


    %>

</form>
<%--<form action="${pageContext.request.contextPath}/match-score?uuid=${uuid}" method="POST">--%>

<%--    <p><input type="submit"  name="win" value="player1" ></p>--%>
<%--    <p><input type="submit" name="win" value="player2" ></p>--%>


<%--</form>--%>

</body>
</html>
