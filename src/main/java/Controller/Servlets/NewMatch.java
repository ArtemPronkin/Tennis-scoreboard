package Controller.Servlets;

import Controller.ServiceMatch.DAO.PlayerDAO;
import Controller.ServiceMatch.Entity.Player;
import Controller.Utils.GameRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

import static Controller.Utils.Validator.playerValid;

@WebServlet(name = "NewMatch", value = "/NewMatch")
public class NewMatch extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/view/start.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String player1 = request.getParameter("player1");
        String player2 = request.getParameter("player2");

        if (!playerValid(player1)||!playerValid(player2)){
            response.sendRedirect(request.getContextPath()+"/new-match");
        }
        else{
            PlayerDAO.create(new Player(player1.trim()));
            PlayerDAO.create(new Player(player2.trim()));
            response.sendRedirect(request.getContextPath()+"/match-score?uuid=" +
                    GameRepository.createMatch(player1.trim(),player2.trim()));

        }
    }
}