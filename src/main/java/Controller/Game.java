package Controller;

import Controller.Utils.GameRepository;
import Score.MatchScore;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "Game", value = "/Game")
public class Game extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nameMatch = request.getParameter("uuid");
        var uuid = UUID.fromString(nameMatch);
        if (!GameRepository.haveMatch(uuid)){
            response.getWriter().write("Матч не найден");
            return;
        }
        var match = GameRepository.getMatch(UUID.fromString(nameMatch));
       var player1_score = match.getPlayerScore(0);
       var player2_score = match.getPlayerScore(0);
       var State = match.getState().toString();
       var player1_set1_score = match.getSetList().get(0).getPlayerScore(0);
       var player2_set1_score = match.getSetList().get(0).getPlayerScore(1);
       var player1_set2_score = match.getSetList().get(1).getPlayerScore(0);
       var player2_set2_score = match.getSetList().get(1).getPlayerScore(1);
       var player1_set3_score = match.getSetList().get(2).getPlayerScore(0);
       var player2_set3_score = match.getSetList().get(2).getPlayerScore(1);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}