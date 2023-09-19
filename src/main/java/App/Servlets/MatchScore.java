package App.Servlets;

import App.ServiceMatch.Matches.GameRepository;
import App.ServiceMatch.MatchesScoreCalculations.ExceptionScore;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "MatchScore", value = "/MatchScore")
public class MatchScore extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nameMatch = request.getParameter("uuid");
        if (nameMatch.isEmpty() || !GameRepository.haveMatch(UUID.fromString(nameMatch))) {
            response.sendRedirect(request.getContextPath() + "/new-match");
            return;
        }
        var uuid = UUID.fromString(nameMatch);
        var match = GameRepository.getMatch(uuid);
        request.setAttribute("uuid", nameMatch);
        request.setAttribute("match", match);
        request.setAttribute("player1",GameRepository.getPlayer1(uuid));
        request.setAttribute("player2",GameRepository.getPlayer2(uuid));
        getServletContext().getRequestDispatcher("/view/match.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String win = request.getParameter("win");
        String nameMatch = request.getParameter("uuid");
        if (nameMatch.isEmpty() || !GameRepository.haveMatch(UUID.fromString(nameMatch))) {
            response.sendRedirect(request.getContextPath() + "/new-match");
            return;
        }
        var match = GameRepository.getMatch(UUID.fromString(nameMatch));
        try {
            if (win.equals("player1")) {
                match.pointWon(0);
            }
            if (win.equals("player2")) {
                match.pointWon(1);
            }

        } catch (ExceptionScore e) {
            response.getWriter().write(e.getMessage());
        }
        if (win.equals("end")) {
            GameRepository.endMatch(UUID.fromString(nameMatch));
            response.sendRedirect(request.getContextPath()+"/");
            return;
        }
        doGet(request,response);
    }
}
