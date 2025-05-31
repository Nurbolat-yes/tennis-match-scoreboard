package by.nurbolat.tennismatchscoreboard.mapper;

import by.nurbolat.tennismatchscoreboard.dto.CreatePlayerDto;
import by.nurbolat.tennismatchscoreboard.entity.Player;

public class PlayerMapper implements Mapper<CreatePlayerDto, Player>{
    private static final PlayerMapper INSTANCE = new PlayerMapper();
    
    private PlayerMapper(){
        
    }

    public static PlayerMapper getInstance() {
        return INSTANCE;
    }


    @Override
    public Player mapFrom(CreatePlayerDto objectFrom) {
        return Player.builder()
                .name(objectFrom.name())
                .build();
    }
}
