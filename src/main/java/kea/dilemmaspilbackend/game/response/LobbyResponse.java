package kea.dilemmaspilbackend.game.response;

import kea.dilemmaspilbackend.game.model.GameLobby;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LobbyResponse {
    private GameLobby gameLobby;
    private String message;
}
