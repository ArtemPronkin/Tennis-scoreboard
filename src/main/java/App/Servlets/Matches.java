package App.Servlets;

import App.ServiceMatch.Matches.PageMatchesDTO;
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
        List<PageMatchesDTO> table = new ArrayList<>();
        int sizePage = 3;
        if (player == null || player.length()==0){
            table = PageMatchesDTO.getPage(Integer.valueOf(page), sizePage);
        }
        else {

             table = PageMatchesDTO.getPageByName(Integer.valueOf(page), 3,player);
        }
        request.setAttribute("player",player);
        request.setAttribute("table",table);
        request.setAttribute("page",page);
        request.setAttribute("sizePage",sizePage);
        getServletContext().getRequestDispatcher("/view/matches.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}