package kea.dilemmaspilbackend.game.service;

import kea.dilemmaspilbackend.game.model.GameLobby;
import kea.dilemmaspilbackend.game.model.persistmodel.GameLobbyPersist;
import kea.dilemmaspilbackend.game.repository.GameLobbyPersistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GameLobbyLoggerService {

    private GameLobbyPersistRepository gameLobbyPersistRepository;

    public void save(GameLobbyPersist gameLobby){
        gameLobbyPersistRepository.save(gameLobby);
    }


}
