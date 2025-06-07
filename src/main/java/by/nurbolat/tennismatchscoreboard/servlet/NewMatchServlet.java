package by.nurbolat.tennismatchscoreboard.servlet;

import by.nurbolat.tennismatchscoreboard.dao.PlayerRepository;
import by.nurbolat.tennismatchscoreboard.dto.CreatePlayerDto;
import by.nurbolat.tennismatchscoreboard.entity.Match;
import by.nurbolat.tennismatchscoreboard.entity.Player;
import by.nurbolat.tennismatchscoreboard.game.CurrentMatch;
import by.nurbolat.tennismatchscoreboard.game.MatchScoreModel;
import by.nurbolat.tennismatchscoreboard.service.OngoingMatchesService;
import by.nurbolat.tennismatchscoreboard.service.PlayerService;
import by.nurbolat.tennismatchscoreboard.util.HibernateUtil;
import by.nurbolat.tennismatchscoreboard.util.JspWrapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspWrapper.toPath("newMatch")).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var sessionFactory = HibernateUtil.getSessionFactory();
        var session = sessionFactory.getCurrentSession();

        session.beginTransaction();
        PlayerRepository playerRepository = new PlayerRepository(session);
        PlayerService playerService = new PlayerService(playerRepository);

        String playerName1 = req.getParameter("player1");
        String playerName2 = req.getParameter("player2");

        CreatePlayerDto createPlayerDto1 = new CreatePlayerDto(playerName1);
        CreatePlayerDto createPlayerDto2 = new CreatePlayerDto(playerName2);

        int player1Id = playerService.create(createPlayerDto1);
        int player2Id = playerService.create(createPlayerDto2);

        Player player1 = session.find(Player.class,player1Id);
        Player player2 = session.find(Player.class,player2Id);

        OngoingMatchesService ongoingMatchesService = OngoingMatchesService.getInstance();
        UUID uuid = ongoingMatchesService.addMatch(new CurrentMatch(player1,player2));

        resp.sendRedirect("/match-score?uuid="+uuid);
        session.getTransaction().commit();
    }
}
