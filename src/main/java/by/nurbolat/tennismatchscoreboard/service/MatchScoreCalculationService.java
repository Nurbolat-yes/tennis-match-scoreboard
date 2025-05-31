package by.nurbolat.tennismatchscoreboard.service;

import by.nurbolat.tennismatchscoreboard.game.CurrentMatch;
import by.nurbolat.tennismatchscoreboard.game.MatchScoreModel;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class MatchScoreCalculationService {
    private static final MatchScoreCalculationService INSTANCE = new MatchScoreCalculationService();

    private OngoingMatchesService ongoingMatchesService = OngoingMatchesService.getInstance();


    public Integer checkWinner(UUID uuid){
        int firstSetScore = ongoingMatchesService.getCurrentMatchByUUID(uuid)
                .getMatchScoreModel().getSetByIndex(0);
        int secondSetScore = ongoingMatchesService.getCurrentMatchByUUID(uuid)
                .getMatchScoreModel().getSetByIndex(1);

        if (firstSetScore >= 6 && firstSetScore > secondSetScore)
            return 0;
        else if (secondSetScore >= 6 && secondSetScore > firstSetScore)
            return 1;

    }


    public Integer add15Point(UUID uuid, Integer index){
        return ongoingMatchesService.getCurrentMatchByUUID(uuid)
                .getMatchScoreModel()
                .addPoint15(index);
    }

    public Integer add10Point(UUID uuid, Integer index){
        return ongoingMatchesService.getCurrentMatchByUUID(uuid)
                .getMatchScoreModel()
                .addPoint10(index);
    }

    public Integer incrementSet(UUID uuid,Integer index){
        return ongoingMatchesService.getCurrentMatchByUUID(uuid)
                .getMatchScoreModel().addSet(index);
    }

    public Integer getCurrentPoint(UUID uuid,Integer index){
        return ongoingMatchesService.getCurrentMatchByUUID(uuid)
                .getMatchScoreModel().getPointByIndex(index);
    }

    public Integer getCurrentSet(UUID uuid,Integer index){
        return ongoingMatchesService.getCurrentMatchByUUID(uuid)
                .getMatchScoreModel().getSetByIndex(index);
    }

    public void dropPoint(UUID uuid, Integer integer){
        ongoingMatchesService.getCurrentMatchMap().get(uuid).getMatchScoreModel().dropPointByIndex(integer);
    }

    public static MatchScoreCalculationService getInstance() {
        return INSTANCE;
    }
}
