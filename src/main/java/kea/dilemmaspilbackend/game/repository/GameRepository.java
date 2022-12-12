package kea.dilemmaspilbackend.game.repository;

import kea.dilemmaspilbackend.game.model.GameLobby;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Data
@Repository
public class GameRepository {

    private Map<String, GameLobby> gameLobbyList;

    public GameRepository() {
        gameLobbyList = new HashMap<>();
    }



}
