package kea.dilemmaspilbackend.game.model;

import kea.dilemmaspilbackend.dilemmas.model.CardPackageModel;
import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
@ToString
@Data
public class GameLobby {
    private String lobbyCode;
    private List<Player> playerList;
    private CardPackageModel cardPackage;
    private int currentRound;
    private int totalRounds;
    private GameLobbyLogger gameLobbyLogger;


    public GameLobby(CardPackageModel cardPackage, Player player) {
        this.lobbyCode = createLobbyCode();
        this.playerList = new ArrayList<>();
        this.cardPackage = cardPackage;
        this.totalRounds = cardPackage.getDilemmaModels().size();
        addPlayer(player);
        currentRound = -1;
    }

    public void addPlayer(Player player){
        playerList.add(player);
    }

    private String createLobbyCode() {
        // Code found at https://www.baeldung.com/java-random-string

        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 5;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }

        return buffer.toString();
    }

    public void advanceGame() {
        //int numberOfReadyPlayers = (int) playerList.stream().filter(Player::isReady).count();
        //if (playerList.size() == numberOfReadyPlayers)
        currentRound++;
    }

    public void endGame() {
        gameLobbyLogger.log(this);
    }

    public void readyUp(Player player) {
        for(Player player1: playerList) {
            if (player1.getName().equals(player.getName()))
                player1.setReady(true);
        }
        advanceGame();
    }
}
