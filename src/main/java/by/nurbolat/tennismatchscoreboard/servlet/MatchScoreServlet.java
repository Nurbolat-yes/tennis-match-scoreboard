package by.nurbolat.tennismatchscoreboard.servlet;

import by.nurbolat.tennismatchscoreboard.entity.Player;
import by.nurbolat.tennismatchscoreboard.game.GameState;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuid = req.getParameter("uuid");

        var currentMatch = ongoingMatchesService.getCurrentMatchByUUID(UUID.fromString(uuid));

        Player player1 = currentMatch.getPlayer1();
        Player player2 = currentMatch.getPlayer2();

        req.getSession().setAttribute("player1",player1);
        req.getSession().setAttribute("player2",player2);


        req.getSession().setAttribute("point1",currentMatch.getMatchScoreModelByPlayerId(player1.getId()).getPoint());
        req.getSession().setAttribute("point2",currentMatch.getMatchScoreModelByPlayerId(player2.getId()).getPoint());

        //when 40 == 40
        if (currentMatch.getMatchScoreModelByPlayerId(player1.getId()).getExtraPoint() == 1)
            req.getSession().setAttribute("point1","AD");
        if (currentMatch.getMatchScoreModelByPlayerId(player2.getId()).getExtraPoint() == 1)
            req.getSession().setAttribute("point2","AD");

        req.getSession().setAttribute("set1",currentMatch.getMatchScoreModelByPlayerId(player1.getId()).getSet());
        req.getSession().setAttribute("set2",currentMatch.getMatchScoreModelByPlayerId(player2.getId()).getSet());

//        System.out.println(currentMatch.getMatchScoreModelByPlayerId(currentMatch.getPlayer1().getId()));
//        System.out.println(currentMatch.getMatchScoreModelByPlayerId(currentMatch.getPlayer2().getId()));

        req.getRequestDispatcher(JspWrapper.toPath("currentMatch")).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        var currentMatch = ongoingMatchesService.getCurrentMatchByUUID(uuid);

        var matchScoreCalculationService = new MatchScoreCalculationService(currentMatch,GameState.CURRENT_GAME);

        Integer scoreWinnerPlayerId = Integer.parseInt(req.getParameter("winner_id"));

        System.out.println(scoreWinnerPlayerId);

        Player player1 = currentMatch.getPlayer1();
        Player player2 = currentMatch.getPlayer2();

        GameState gameState = matchScoreCalculationService.getGameState(player1,player2);

        if (gameState.equals(GameState.EXTRA_POINT_GAME)) {
            System.out.println(gameState);
            gameState = matchScoreCalculationService.extraPointGame(player1,player2,scoreWinnerPlayerId);

            doGet(req,resp);
//            req.getRequestDispatcher(JspWrapper.toPath("currentMatch")).forward(req,resp);
        }
        if (gameState.equals(GameState.EXTRA_SET_GAME)) {
            System.out.println(gameState);
            gameState = matchScoreCalculationService.extraSetGame(player1,player2,scoreWinnerPlayerId);

            doGet(req,resp);
//            req.getRequestDispatcher(JspWrapper.toPath("currentMatch")).forward(req,resp);
        }
        if (gameState.equals(GameState.CURRENT_GAME)) {
            System.out.println(gameState);

            gameState = matchScoreCalculationService.addScoreToWinner(player1,player2,scoreWinnerPlayerId);

            doGet(req,resp);
//            req.getRequestDispatcher(JspWrapper.toPath("currentMatch")).forward(req,resp);

        }
        if (gameState.equals(GameState.FINISHED_GAME)){
            System.out.println(gameState);

            Player winnerPlayer = matchScoreCalculationService.checkWinnerGame(player1,player2);

            System.out.println(winnerPlayer);
            System.out.println(player1.getName()+ " : "+ matchScoreCalculationService.getScoresByPlayerId(player1));
            System.out.println(player2.getName()+ " : "+ matchScoreCalculationService.getScoresByPlayerId(player2));

            req.getSession().setAttribute("listOfWinners",matchScoreCalculationService.getScoreResults());

            doGet(req,resp);
            return;
        }

        showPlayersScore(player1,player2,matchScoreCalculationService);

    }

    public void showPlayersScore(Player player1,Player player2,MatchScoreCalculationService matchScoreCalculationService){
        System.out.println("Player 1 : " + player1.getName() + " Set : "
                + matchScoreCalculationService.getSetByPlayerId(player1.getId()) + " Point : "
                + matchScoreCalculationService.getPointByPlayerId(player1.getId()) + " Extra Point : "
                + matchScoreCalculationService.getExtraPointByPlayerId(player1.getId() )
        );

        System.out.println("Player 2 : " + player2.getName() + " Set : "
                + matchScoreCalculationService.getSetByPlayerId(player2.getId()) + " Point : "
                + matchScoreCalculationService.getPointByPlayerId(player2.getId()) + " Extra Point : "
                + matchScoreCalculationService.getExtraPointByPlayerId(player2.getId() )
        );

        System.out.println();
    }
}
