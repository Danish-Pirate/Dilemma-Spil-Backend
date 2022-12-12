package kea.dilemmaspilbackend.game.service;

import kea.dilemmaspilbackend.dilemmas.model.CardPackageModel;
import kea.dilemmaspilbackend.dilemmas.repository.service.CardPackageService;
import kea.dilemmaspilbackend.game.model.GameLobby;
import kea.dilemmaspilbackend.game.model.Player;
import kea.dilemmaspilbackend.game.repository.GameRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GameService {
    private GameRepository gameRepository;
    private CardPackageService cardPackageService;

    public GameLobby createGameLobby(Player player, int cardPackageID) {

        Optional<CardPackageModel> byId = cardPackageService.findById(cardPackageID);


        CardPackageModel cardPackage = byId.orElse(null);

        GameLobby gameLobby = new GameLobby(cardPackage, player);

        gameRepository.getGameLobbyList().put(gameLobby.getLobbyCode(), gameLobby);

        return gameLobby;
    }

    public void joinGameLobby(Player player, String lobbyCode) {
        GameLobby gameLobby = gameRepository.getGameLobbyList().get(lobbyCode);
        gameLobby.getPlayerList().add(player);
    }

    public GameLobby fetchGameLobbyFromLobbyCode(String key) {
        return gameRepository.getGameLobbyList().get(key);
    }

    public void removeGameLobby(String lobbyCode) {
        gameRepository.getGameLobbyList().remove(lobbyCode);
    }

    // The endGame method saves the relevant statistical information from the gamelobby to the database and removes the gamelobby.
    public void endGame(String lobbyCode) {
        gameRepository.getGameLobbyList().get(lobbyCode).endGame();
        removeGameLobby(lobbyCode);
    }

    public void leaveGameLobby(Player player, String lobbyCode) {
        List<Player> playerList = gameRepository.getGameLobbyList().get(lobbyCode).getPlayerList();
        for (int i = 0; i < playerList.size(); i++) {
            if (playerList.get(i).getName().equals(player.getName())) {
                playerList.remove(i);
                return;
            }
        }
    }

    public int setCurrentRound(String lobby, int currentRound) {
        fetchGameLobbyFromLobbyCode(lobby).setCurrentRound(currentRound);

        return currentRound;
    }

    public void readyUp(Player player, String lobbyCode) {
        GameLobby gameLobbyList = gameRepository.getGameLobbyList().get(lobbyCode);
        gameLobbyList.readyUp(player);
    }

    public void addPlayerToLobby(String key, Player player) {
        gameRepository.getGameLobbyList().get(key).addPlayer(player);
    }


    public boolean lobbyExist(String key) {
        Map<String, GameLobby> gameLobbyList = gameRepository.getGameLobbyList();

        GameLobby gameLobby = gameLobbyList.get(key);

        return gameLobby != null;
    }


    public List<GameLobby> getAllLobby() {
        return new ArrayList<>(gameRepository.getGameLobbyList().values());
    }
}
