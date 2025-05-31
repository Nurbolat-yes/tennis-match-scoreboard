package by.nurbolat.tennismatchscoreboard.game;

import by.nurbolat.tennismatchscoreboard.entity.Player;
import jakarta.persistence.criteria.CriteriaBuilder;

public class MatchScoreModel {
    private int points[] = new int[2];
    private int sets[] = new int[2];

    public boolean isSetsEqual(){
        return sets[0] == sets[1];
    }

    public boolean isPointsEqual(){
        return points[0] == points[1];
    }

    public int addPoint15(Integer playerNumber){
        return points[playerNumber] += 15;
    }

    public int addPoint10(Integer playerNumber){
        return points[playerNumber] += 10;
    }

    public int addSet(Integer playerNumber){
        return sets[playerNumber]+=1;
    }

    public int getPointByIndex(Integer index){
        return points[index];
    }

    public int getSetByIndex(Integer index){
        return sets[index];
    }

    public void dropPointByIndex(Integer integer){
        points[integer] = 0;
    }

    public void dropSetByIndex(Integer integer){
        sets[integer] = 0;
    }

    public void resetScores(){
        for (int i = 0; i < 2; i++) {
            points[i] = 0;
            sets[i] = 0;
        }
    }

}
