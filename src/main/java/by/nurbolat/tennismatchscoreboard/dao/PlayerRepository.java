package by.nurbolat.tennismatchscoreboard.dao;

import by.nurbolat.tennismatchscoreboard.dto.CreatePlayerDto;
import by.nurbolat.tennismatchscoreboard.entity.Player;
import jakarta.persistence.EntityManager;
import org.hibernate.SessionFactory;

import java.util.List;

public class PlayerRepository extends BaseRepository<Integer, Player>{
    public PlayerRepository(EntityManager entityManager) {
        super(Player.class, entityManager);
    }

    public boolean isPlayerExist(Player player){

        for (Player p:findAll()){
            if (p.getName().equals(player.getName())){
                player.setId(p.getId());
                return true;
            }
        }

        return false;
    }

}
