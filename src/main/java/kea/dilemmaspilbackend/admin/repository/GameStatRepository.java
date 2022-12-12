package kea.dilemmaspilbackend.admin.repository;

import kea.dilemmaspilbackend.game.model.GameLobbyLogger;
import kea.dilemmaspilbackend.game.model.persistmodel.GameLobbyPersist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameStatRepository extends JpaRepository<GameLobbyPersist, Long> {

}
