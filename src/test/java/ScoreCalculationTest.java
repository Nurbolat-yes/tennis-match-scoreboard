import by.nurbolat.tennismatchscoreboard.entity.Player;
import by.nurbolat.tennismatchscoreboard.game.CurrentMatch;
import by.nurbolat.tennismatchscoreboard.game.GameState;
import by.nurbolat.tennismatchscoreboard.game.MatchScoreModel;
import by.nurbolat.tennismatchscoreboard.service.MatchScoreCalculationService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreCalculationTest {

    @Test
    void winnerPlayer1Test(){
        Player player1 = new Player(1,"Nurbolat");
        Player player2 = new Player(2,"DeLacure");
        CurrentMatch currentMatch = new CurrentMatch(player1,player2);


        MatchScoreCalculationService matchScoreCalculationService = new MatchScoreCalculationService(currentMatch,GameState.CURRENT_GAME);

        currentMatch.getMatchScoreModelByPlayerId(player1.getId()).addSet(6);
        currentMatch.getMatchScoreModelByPlayerId(player2.getId()).addSet(3);

        assertEquals(player1,matchScoreCalculationService.checkWinnerGame(player1,player2));
    }
}
