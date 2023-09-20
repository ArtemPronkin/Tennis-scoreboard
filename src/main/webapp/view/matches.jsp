<%@ page import="App.ServiceMatch.Matches.PageMatchesDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

    List<PageMatchesDTO> table = (List<PageMatchesDTO>) request.getAttribute("table");

%>
<html>
<head>
    <h3>Таблица завершенных матчей</h3>
    <h3>Всего матчей сыграно: <% if (table.isEmpty()) {
        out.print(0);
    } else out.print(table.get(0).getTotalCount()); %></h3>
    <style> table {
        border-collapse: collapse;
        width: 600px;
        border: 1px solid green;
        margin: auto;
    }

    td, th {
        text-align: center;
        padding: 5px;
        border: 1px solid green;
        width: 200px;
    }

    h3 {
        text-align: center;
        margin-top: 15px;
    }

    form {
        margin: auto;
        text-align: center;
    }

    div {
        text-align: center;
        margin-top: 10px;
    }

    a {
        margin-right: 5px;
    }
    </style>
    <title>Таблица матчей</title>
</head>
<body>
<% if (table.isEmpty()) {
    out.print("<h3>Матчи с этим игроком не найдены</h3>");
} else {
    out.print("<table>\n" +
            "    <thead>\n" +
            "    <tr>\n" +
            "        <th>Игрок 1</th>\n" +
            "\n" +
            "        <th>Игрок 2</th>\n" +
            "\n" +
            "        <th>Победитель</th>\n" +
            "\n" +
            "    </tr>\n" +
            "    </thead>\n" +
            "    <tbody>");
    for (PageMatchesDTO match :
            table) {
        out.print("<tr>");
        out.print("<td>" + match.getPlayer1() + "</td>");
        out.print("<td>" + match.getPlayer2() + "</td>");
        out.print("<td>" + match.getWinner() + "</td>");
        out.print("</tr>");
    }
    out.print("    </tbody>\n" +
            "</table>");


}%>

<div>
    <% String player = request.getParameter("player");
        if (player == null || player.length() == 0) {
            player = "";
        } else {
            out.print("<h3>Поиск по игроку: " + player + "</h3>");
        }
        if (table.isEmpty()) {
            out.print("<div><button type=\"reset\" onclick=\"location.href='" + request.getContextPath() + "/matches'\">Ко всем матчам</button></div>");
        } else {
            for (int i = 0; i * PageMatchesDTO.sizePage < table.get(0).getTotalCount(); i++) {
                out.print("<a href=?page=" + (i + 1) + "&player=" + player.replace(' ', '+') + ">" + (i + 1) + "</a>");
            }
        }
    %>
</div>
<div>
    <button type="reset" onclick="location.href='${pageContext.request.contextPath}/'">На главную</button>
    <button type="reset" onclick="location.href='${pageContext.request.contextPath}/matches'">Отобразить все матчи
    </button>
</div>
<div>
    <form action="${pageContext.request.contextPath}/matches" method="GET">

        <label>Поиск по имени:
            <input type="text" name="player" required title="Введите имя игрока"><br/>
        </label>
        <div>
            <button type="submit">поиск</button>
        </div>
    </form>
</div>
</body>
</html>
