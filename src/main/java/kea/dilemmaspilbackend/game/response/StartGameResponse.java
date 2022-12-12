package kea.dilemmaspilbackend.game.response;

import kea.dilemmaspilbackend.dilemmas.model.CardPackageModel;
import kea.dilemmaspilbackend.dilemmas.model.DilemmaModel;
import kea.dilemmaspilbackend.game.model.GameLobby;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class StartGameResponse {


    private GameLobby gameLobby;
    private CardPackageModel cardPackage;
    private String message;
    private int currentRound;


}
