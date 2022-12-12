package kea.dilemmaspilbackend.game.model.persistmodel;

import kea.dilemmaspilbackend.dilemmas.model.CardPackageModel;
import kea.dilemmaspilbackend.game.model.GameLobby;
import kea.dilemmaspilbackend.game.model.Player;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class GameLobbyPersist {
    @Id
    private Long id;
    @OneToMany
    private List<PlayerPersist> playerPersistList;
    @OneToOne
    private CardPackageModel cardPackage;

    public GameLobbyPersist(GameLobby gameLobby) {
        this.cardPackage = gameLobby.getCardPackage();
        this.playerPersistList = convertPlayerToPlayerPersist(gameLobby.getPlayerList());
    }

    private List<PlayerPersist> convertPlayerToPlayerPersist(List<Player> playerList) {
        List<PlayerPersist> playerPersists = new ArrayList<>();


        for (Player player: playerList) {
            playerPersists.add(new PlayerPersist(player.getListOfRoundResponses(), player.getGroupFieldOfStudy()));
        }
        return playerPersists;
    }
}
