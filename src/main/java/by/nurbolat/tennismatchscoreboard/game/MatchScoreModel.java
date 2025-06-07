package by.nurbolat.tennismatchscoreboard.game;

import by.nurbolat.tennismatchscoreboard.entity.Player;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@ToString
public class MatchScoreModel {
    private Integer set;
    private Integer point;
    //helps for calculating extra game when points are 40 = 40
    private Integer extraPoint;

    public MatchScoreModel(Integer set, Integer point, Integer extraPoint) {
        this.set = set;
        this.point = point;
        this.extraPoint = extraPoint;
    }



    public Integer getSet() {
        return set;
    }

    public void addSet(Integer set) {
        this.set += set;
    }

    public Integer getPoint() {
        return point;
    }

    public void addPoint(Integer point) {
        this.point += point;
    }

    public Integer getExtraPoint() {
        return extraPoint;
    }

    public void addExtraPoint(Integer extraPoint) {
        this.extraPoint += extraPoint ;
    }

    public void clearPoint(){
        this.point = 0;
    }

    public void clearSet(){
        this.set = 0;
    }

    public void clearExtraPoint(){
        this.extraPoint = 0;
    }

    public void clearScore(){
        this.set = 0;
        this.point = 0;
        this.extraPoint = 0;
    }
}
