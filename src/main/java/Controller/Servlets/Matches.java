package Controller.Servlets;

import Controller.ServiceMatch.MatchesDTO.PageMatchesDTO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Matches", value = "/matches")
public class Matches extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page");
        String player = request.getParameter("player");
        if(page == null){
            page = "1";
        }
        System.out.println(player);
        List<PageMatchesDTO> table = new ArrayList<>();
        if (player == null || player.length()==0){
            table = PageMatchesDTO.getPage(Integer.valueOf(page), 3);
        }
        else {
             table = PageMatchesDTO.getPageByName(Integer.valueOf(page), 3,player);
        }
        request.setAttribute("player",player);
        request.setAttribute("table",table);
        request.setAttribute("page",page);
        getServletContext().getRequestDispatcher("/view/matches.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}