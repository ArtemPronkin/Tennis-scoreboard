package Controller;

import Controller.Utils.GameRepository;
import Controller.Utils.Validator;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

import static Controller.Utils.Validator.playerValid;

@WebServlet(name = "Start", value = "/Start")
public class Start extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            String player1 = request.getParameter("player1");
            String player2 = request.getParameter("player2");

            if (playerValid(player1)&&playerValid(player2)){
            response.sendRedirect(request.getContextPath()+"/game/"+ GameRepository.createMatch(player1,player2));
            }
            else{
                getServletContext().getRequestDispatcher("/match.jspt").forward(request,response);
            }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}