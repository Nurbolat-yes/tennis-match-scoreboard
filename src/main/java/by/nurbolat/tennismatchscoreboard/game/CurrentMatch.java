package by.nurbolat.tennismatchscoreboard.game;

import by.nurbolat.tennismatchscoreboard.entity.Player;
import lombok.Getter;

import java.util.*;

public class CurrentMatch {
    @Getter
    private Player player1;
    @Getter
    private Player player2;

    private Map<Integer,MatchScoreModel> scoresOfPlayers = new HashMap<>(2);

    public CurrentMatch(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        scoresOfPlayers.put(player1.getId(),new MatchScoreModel(0,0,0));
        scoresOfPlayers.put(player2.getId(),new MatchScoreModel(0,0,0));
    }

    public MatchScoreModel getMatchScoreModelByPlayerId(Integer playerId){
        return scoresOfPlayers.get(playerId);
    }


}
