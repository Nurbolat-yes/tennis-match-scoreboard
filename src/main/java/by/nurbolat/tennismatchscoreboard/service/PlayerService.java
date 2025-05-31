package by.nurbolat.tennismatchscoreboard.service;

import by.nurbolat.tennismatchscoreboard.dao.PlayerRepository;
import by.nurbolat.tennismatchscoreboard.dto.CreatePlayerDto;
import by.nurbolat.tennismatchscoreboard.entity.Player;
import by.nurbolat.tennismatchscoreboard.mapper.PlayerMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper = PlayerMapper.getInstance();

    public Integer create(CreatePlayerDto createPlayerDto){
        //validator
        Player player = playerMapper.mapFrom(createPlayerDto);

        if (playerRepository.isPlayerExist(player)){
            return player.getId();
        }

        return playerRepository.save(player).getId();
    }
}
