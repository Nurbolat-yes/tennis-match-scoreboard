package by.nurbolat.tennismatchscoreboard.game;

import by.nurbolat.tennismatchscoreboard.entity.Player;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
public class CurrentMatch {
    private Player player1;
    private Player player2;

    private MatchScoreModel matchScoreModel;

    public CurrentMatch(MatchScoreModel matchScoreModel,Player player1, Player player2){
        this.matchScoreModel = matchScoreModel;
        this.player1 = player1;
        this.player2 = player2;
    }

}
