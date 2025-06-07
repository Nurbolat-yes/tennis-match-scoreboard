package by.nurbolat.tennismatchscoreboard.service;

import by.nurbolat.tennismatchscoreboard.entity.Player;
import by.nurbolat.tennismatchscoreboard.game.CurrentMatch;
import by.nurbolat.tennismatchscoreboard.game.GameState;
import by.nurbolat.tennismatchscoreboard.game.MatchScoreModel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchScoreCalculationService {
    private CurrentMatch currentMatch;
    private GameState gameState = GameState.CURRENT_GAME;
    @Getter
    private Map<Player,MatchScoreModel> scoreResults = new HashMap<>();

    public MatchScoreCalculationService(CurrentMatch currentMatch, GameState gameState) {
        this.currentMatch = currentMatch;
        this.gameState = gameState;
    }

    public GameState getGameState(Player player1, Player player2){
        int setOfPlayer1 = currentMatch.getMatchScoreModelByPlayerId(player1.getId()).getSet();
        int setOfPlayer2 = currentMatch.getMatchScoreModelByPlayerId(player2.getId()).getSet();

        int pointOfPlayer1 = currentMatch.getMatchScoreModelByPlayerId(player1.getId()).getPoint();
        int pointOfPlayer2 = currentMatch.getMatchScoreModelByPlayerId(player2.getId()).getPoint();

        boolean difference1 = (setOfPlayer1 - setOfPlayer2) >= 2 && setOfPlayer1 == 6;
        boolean difference2 = (setOfPlayer2 - setOfPlayer1) >= 2 && setOfPlayer2 == 6;

        // when 7 > 6
        if (setOfPlayer1 > setOfPlayer2 && setOfPlayer1 == 7){
            gameState = GameState.FINISHED_GAME;
            saveScores(player1,player2);
            clearAllScores();
            return gameState;
        } else if (setOfPlayer2 > setOfPlayer1 && setOfPlayer2 == 7){
            gameState = GameState.FINISHED_GAME;
            saveScores(player1,player2);
            clearAllScores();
            return gameState;
        }

        // when 6 > 4 or 5 > 2 or 6 > 5
        if (setOfPlayer1 > setOfPlayer2 && difference1){
            gameState = GameState.FINISHED_GAME;
            saveScores(player1,player2);
            clearAllScores();
            return gameState;
        } else if (setOfPlayer2 > setOfPlayer1 && difference2) {
            gameState = GameState.FINISHED_GAME;
            saveScores(player1,player2);
            clearAllScores();
            return gameState;
        }

        // when sets 6 = 6
        if (setOfPlayer1 == 6 && setOfPlayer2 == 6){
            gameState = GameState.EXTRA_SET_GAME;
            return gameState;
        }

        // when points 40 = 40
        if (pointOfPlayer1 == 40 && pointOfPlayer2 == 40){
            gameState = GameState.EXTRA_POINT_GAME;
            return gameState;
        }

        return GameState.CURRENT_GAME;
    }

    private void saveScores(Player player1,Player player2) {
        int set1 = currentMatch.getMatchScoreModelByPlayerId(player1.getId()).getSet();
        int point1 = currentMatch.getMatchScoreModelByPlayerId(player1.getId()).getPoint();
        int extraPoint1= currentMatch.getMatchScoreModelByPlayerId(player1.getId()).getExtraPoint();

        int set2 = currentMatch.getMatchScoreModelByPlayerId(player2.getId()).getSet();
        int point2 = currentMatch.getMatchScoreModelByPlayerId(player2.getId()).getPoint();
        int extraPoint2 = currentMatch.getMatchScoreModelByPlayerId(player2.getId()).getExtraPoint();

        var score1 = new MatchScoreModel(set1,point1,extraPoint1);
        var score2 = new MatchScoreModel(set2,point2,extraPoint2);

        scoreResults.put(player1,score1);
        scoreResults.put(player2,score2);
    }

    public Player checkWinnerGame(Player player1,Player player2){
        int set1 = scoreResults.get(player1).getSet();
        int set2 = scoreResults.get(player2).getSet();
        if (set1 > set2){
            return player1;
        }
        return player2;
    }

    public MatchScoreModel getScoresByPlayerId(Player player){
        return scoreResults.get(player);
    }

    public GameState addScoreToWinner(Player player1, Player player2, Integer scoreWinnerPlayerId) {
        // point adds 10 15
        if (getPointByPlayerId(scoreWinnerPlayerId) == 0 || getPointByPlayerId(scoreWinnerPlayerId) == 15){
            add15PointsToPlayer(scoreWinnerPlayerId);
        } else if (getPointByPlayerId(scoreWinnerPlayerId) == 30){
            add10PointsToPlayerId(scoreWinnerPlayerId);
        } else if (getPointByPlayerId(scoreWinnerPlayerId) == 40) {
            clearPointByPlayerId(player1.getId());
            clearPointByPlayerId(player2.getId());
            increaseSetByPlayerId(scoreWinnerPlayerId);
        }

        int setOfPlayer1 = currentMatch.getMatchScoreModelByPlayerId(player1.getId()).getSet();
        int setOfPlayer2 = currentMatch.getMatchScoreModelByPlayerId(player2.getId()).getSet();

        boolean difference1 = (setOfPlayer1 - setOfPlayer2) >= 2 && setOfPlayer1 >= 6;
        boolean difference2 = (setOfPlayer2 - setOfPlayer1) >= 2 && setOfPlayer2 >= 6;

        if (setOfPlayer1 > setOfPlayer2 && difference1){
            gameState = GameState.FINISHED_GAME;
            saveScores(player1,player2);
            clearAllScores();
            return gameState;
        } else if (setOfPlayer2 > setOfPlayer1 && difference2) {
            gameState = GameState.FINISHED_GAME;
            saveScores(player1,player2);
            clearAllScores();
            return gameState;
        }

        return gameState;
    }

    public GameState extraSetGame(Player player1, Player player2, Integer scoreWinnerPlayerId) {
        increasePointByPlayerId(scoreWinnerPlayerId);

        int pointOfPlayer1 = currentMatch.getMatchScoreModelByPlayerId(player1.getId()).getPoint();
        int pointOfPlayer2 = currentMatch.getMatchScoreModelByPlayerId(player2.getId()).getPoint();

        boolean difference1 = (pointOfPlayer1 - pointOfPlayer2) >= 2;
        boolean difference2 = (pointOfPlayer2 - pointOfPlayer1) >= 2;

        if (pointOfPlayer1 > pointOfPlayer2 && difference1){
            clearPointByPlayerId(player1.getId());
            clearPointByPlayerId(player2.getId());

            increaseSetByPlayerId(player1.getId());
            gameState = GameState.FINISHED_GAME;
            saveScores(player1,player2);
            clearAllScores();
            return gameState;
        } else if (pointOfPlayer2 > pointOfPlayer1 && difference2) {
            clearPointByPlayerId(player1.getId());
            clearPointByPlayerId(player2.getId());

            increaseSetByPlayerId(player2.getId());
            gameState = GameState.FINISHED_GAME;
            saveScores(player1,player2);
            clearAllScores();
            return gameState;
        }

        return gameState;

    }

    public GameState extraPointGame(Player player1, Player player2, Integer scoreWinnerPlayerId) {
        increaseExtraPointByPlayerId(scoreWinnerPlayerId);

        int extraPointOfPlayer1 = currentMatch.getMatchScoreModelByPlayerId(player1.getId()).getExtraPoint();
        int extraPointOfPlayer2 = currentMatch.getMatchScoreModelByPlayerId(player2.getId()).getExtraPoint();

        boolean difference1 = (extraPointOfPlayer1 - extraPointOfPlayer2) >= 2;
        boolean difference2 = (extraPointOfPlayer2 - extraPointOfPlayer1) >= 2;

        if (extraPointOfPlayer1 > extraPointOfPlayer2 && difference1){
            clearExtraPointByPlayerId(player1.getId());
            clearExtraPointByPlayerId(player2.getId());

            clearPointByPlayerId(player1.getId());
            clearPointByPlayerId(player2.getId());

            increaseSetByPlayerId(player1.getId());

            int setOfPlayer1 = currentMatch.getMatchScoreModelByPlayerId(player1.getId()).getSet();
            int setOfPlayer2 = currentMatch.getMatchScoreModelByPlayerId(player2.getId()).getSet();

            boolean difference11 = (setOfPlayer1 - setOfPlayer2) >= 2 && setOfPlayer1 >= 6;
            boolean difference22 = (setOfPlayer2 - setOfPlayer1) >= 2 && setOfPlayer2 >= 6;

            if (setOfPlayer1 == 6 && 6 == setOfPlayer2){
                return gameState;
            } else if (setOfPlayer1 > setOfPlayer2 && difference11){
                gameState = GameState.FINISHED_GAME;
                saveScores(player1,player2);
                clearAllScores();
                return gameState;
            } else if (setOfPlayer2 > setOfPlayer1 && difference22) {
                gameState = GameState.FINISHED_GAME;
                saveScores(player1,player2);
                clearAllScores();
                return gameState;
            }

//            gameState = GameState.CURRENT_GAME;
            return gameState;
        } else if (extraPointOfPlayer2 > extraPointOfPlayer1 && difference2) {
            clearExtraPointByPlayerId(player1.getId());
            clearExtraPointByPlayerId(player2.getId());

            clearPointByPlayerId(player1.getId());
            clearPointByPlayerId(player2.getId());

            increaseSetByPlayerId(player2.getId());

            int setOfPlayer1 = currentMatch.getMatchScoreModelByPlayerId(player1.getId()).getSet();
            int setOfPlayer2 = currentMatch.getMatchScoreModelByPlayerId(player2.getId()).getSet();

            boolean difference11 = (setOfPlayer1 - setOfPlayer2) >= 2 && setOfPlayer1 >= 6;
            boolean difference22 = (setOfPlayer2 - setOfPlayer1) >= 2 && setOfPlayer2 >= 6;

            if (setOfPlayer1 == 6 && 6 == setOfPlayer2){
                return gameState;
            }else if (setOfPlayer1 > setOfPlayer2 && difference11){
                gameState = GameState.FINISHED_GAME;
                saveScores(player1,player2);
                clearAllScores();
                return gameState;
            } else if (setOfPlayer2 > setOfPlayer1 && difference22) {
                gameState = GameState.FINISHED_GAME;
                saveScores(player1,player2);
                clearAllScores();
                return gameState;
            }

//            gameState = GameState.CURRENT_GAME;
            return gameState;
        } else if (extraPointOfPlayer2 == extraPointOfPlayer1){
            clearExtraPointByPlayerId(player1.getId());
            clearExtraPointByPlayerId(player2.getId());
        }

        return gameState;

    }


    public void add15PointsToPlayer(Integer playerId){
        currentMatch.getMatchScoreModelByPlayerId(playerId).addPoint(15);
    }

    public void add10PointsToPlayerId(Integer playerId){
        currentMatch.getMatchScoreModelByPlayerId(playerId).addPoint(10);
    }

    public void increasePointByPlayerId(Integer playerId){
        currentMatch.getMatchScoreModelByPlayerId(playerId).addPoint(1);
    }

    public void increaseSetByPlayerId(Integer playerId){
        currentMatch.getMatchScoreModelByPlayerId(playerId).addSet(1);
    }

    public void increaseExtraPointByPlayerId(Integer playerId){
        currentMatch.getMatchScoreModelByPlayerId(playerId).addExtraPoint(1);
    }

    public void clearPointByPlayerId(Integer playerId){
        currentMatch.getMatchScoreModelByPlayerId(playerId).clearPoint();
    }

    public void clearSetByPlayerId(Integer playerId){
        currentMatch.getMatchScoreModelByPlayerId(playerId).clearSet();
    }

    public void clearExtraPointByPlayerId(Integer playerId){
        currentMatch.getMatchScoreModelByPlayerId(playerId).clearExtraPoint();
    }

    public void clearScoreByPlayerId(Integer playerId){
        currentMatch.getMatchScoreModelByPlayerId(playerId).clearScore();
    }

    public Integer getPointByPlayerId(Integer playerId){
        return currentMatch.getMatchScoreModelByPlayerId(playerId).getPoint();
    }

    public Integer getSetByPlayerId(Integer playerId){
        return currentMatch.getMatchScoreModelByPlayerId(playerId).getSet();
    }

    public Integer getExtraPointByPlayerId(Integer playerId){
        return currentMatch.getMatchScoreModelByPlayerId(playerId).getExtraPoint();
    }

    public void clearAllScores(){
        clearScoreByPlayerId(currentMatch.getPlayer1().getId());
        clearScoreByPlayerId(currentMatch.getPlayer2().getId());
    }

}
