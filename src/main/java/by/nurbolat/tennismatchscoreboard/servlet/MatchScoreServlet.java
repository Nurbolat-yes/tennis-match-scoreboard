package by.nurbolat.tennismatchscoreboard.servlet;

import by.nurbolat.tennismatchscoreboard.game.CurrentMatch;
import by.nurbolat.tennismatchscoreboard.game.MatchScoreModel;
import by.nurbolat.tennismatchscoreboard.service.MatchScoreCalculationService;
import by.nurbolat.tennismatchscoreboard.service.OngoingMatchesService;
import by.nurbolat.tennismatchscoreboard.util.JspWrapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {
    private OngoingMatchesService ongoingMatchesService = OngoingMatchesService.getInstance();
    private MatchScoreCalculationService matchScoreCalculationService = MatchScoreCalculationService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuid = req.getParameter("uuid");

        var currentMatch = ongoingMatchesService.getCurrentMatchByUUID(UUID.fromString(uuid));

        req.getSession().setAttribute("player1",currentMatch.getPlayer1());
        req.getSession().setAttribute("player2",currentMatch.getPlayer2());

        req.getRequestDispatcher(JspWrapper.toPath("currentMatch")).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
//        var currentMatch = ongoingMatchesService.getCurrentMatchByUUID(uuid);
//        matchScoreCalculationService.checkWinner(uuid)

        String command = req.getParameter("command");
        matchScoreCalculationService

        if (command.equals("Score for 1")){


            int currentPoint = matchScoreCalculationService.getCurrentPoint(uuid,0);
            int currentSet = matchScoreCalculationService.getCurrentSet(uuid,0);

            if (currentPoint == 40){

                currentSet = matchScoreCalculationService.incrementSet(uuid,0);
                matchScoreCalculationService.dropPoint(uuid,0);
                currentPoint = 0;

            } else if (currentPoint == 0 || currentPoint == 15) {

                currentPoint = matchScoreCalculationService.add15Point(uuid,0);

            } else if (currentPoint == 30) {

                currentPoint = matchScoreCalculationService.add10Point(uuid,0);

            }


            req.setAttribute("point1",currentPoint);
            req.setAttribute("set1",currentSet);


            System.out.println("Button 1");
        } else if (command.equals("Score for 2")) {


            System.out.println("Button 2");
        } else {


            System.out.println("Wrong request");
        }

        req.getRequestDispatcher(JspWrapper.toPath("currentMatch")).forward(req,resp);
    }
}
