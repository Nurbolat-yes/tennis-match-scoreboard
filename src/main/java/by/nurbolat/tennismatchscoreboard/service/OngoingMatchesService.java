package by.nurbolat.tennismatchscoreboard.service;

import by.nurbolat.tennismatchscoreboard.game.CurrentMatch;
import lombok.*;
import org.eclipse.tags.shaded.org.apache.bcel.generic.INSTANCEOF;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OngoingMatchesService {
    private static final OngoingMatchesService INSTANCE = new OngoingMatchesService();

    private Map<UUID, CurrentMatch> currentMatchMap = new HashMap<>();

    public static OngoingMatchesService getInstance() {
        return INSTANCE;
    }

    public UUID addMatch(CurrentMatch currentMatch){
        UUID uuid = UUID.randomUUID();
        currentMatchMap.put(uuid,currentMatch);
        return uuid;
    }

    public CurrentMatch getCurrentMatchByUUID(UUID uuid){
        return getCurrentMatchMap().get(uuid);
    }

}
