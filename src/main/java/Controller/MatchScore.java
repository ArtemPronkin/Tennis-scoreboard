package Controller;

import Controller.Utils.GameRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.UUID;

import static Controller.Utils.Validator.playerValid;

@WebServlet(name = "MatchScore", value = "/MatchScore")
public class MatchScore extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nameMatch = request.getParameter("uuid");
        var uuid = UUID.fromString(nameMatch);
        if (!GameRepository.haveMatch(uuid)){
            response.sendRedirect(request.getContextPath()+"/new-match");
            return;
        }
        var match = GameRepository.getMatch(UUID.fromString(nameMatch));
        getServletContext().getRequestDispatcher("/match.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nameMatch = request.getParameter("name");
    }
}