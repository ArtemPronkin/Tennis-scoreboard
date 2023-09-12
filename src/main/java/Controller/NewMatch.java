package Controller;

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
        getServletContext().getRequestDispatcher("/jsp-start").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String player1 = request.getParameter("player1");
        String player2 = request.getParameter("player2");

        if (!playerValid(player1)||!playerValid(player2)){
            response.sendRedirect(request.getContextPath()+"/new-match");
        }
        else{
            response.sendRedirect(request.getContextPath()+"/match-score?uuid=" + GameRepository.createMatch(player1,player2));
        }
    }
}