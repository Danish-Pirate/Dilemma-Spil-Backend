package kea.dilemmaspilbackend.game.controller;
import kea.dilemmaspilbackend.dilemmas.model.CardPackageModel;
import kea.dilemmaspilbackend.dilemmas.repository.service.CardPackageService;
import kea.dilemmaspilbackend.game.model.GameLobby;
import kea.dilemmaspilbackend.game.model.Player;
import kea.dilemmaspilbackend.game.response.LobbyResponse;
import kea.dilemmaspilbackend.game.response.NextCardResponse;
import kea.dilemmaspilbackend.game.response.StartGameResponse;
import kea.dilemmaspilbackend.game.service.GameService;
import lombok.AllArgsConstructor;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Optional;


@AllArgsConstructor
@Controller
class GameController {

    private GameService gameService;
    private CardPackageService cardService;

    @MessageExceptionHandler
    @SendTo("/topic/errors")
    public String handleException(IllegalArgumentException e) {
        var message = ("an exception occurred! " + NestedExceptionUtils.getMostSpecificCause(e));
        System.out.println(message);
        return message;
    }

    @MessageMapping("/game/{lobby}")
    @SendTo("/topic/greetings/{lobby}")
    public LobbyResponse joinLobby(@DestinationVariable String lobby, Player player) throws Exception {
        // player is a json request


        LobbyResponse lobbyResponse = new LobbyResponse();

        gameService.addPlayerToLobby(lobby, player);

        GameLobby gameLobby = gameService.fetchGameLobbyFromLobbyCode(lobby);

        lobbyResponse.setGameLobby(gameLobby);

        lobbyResponse.setMessage("joined Lobby: " + lobby + " added player name " + player.getName());

        return lobbyResponse;
    }


    @MessageMapping("/game/start/{lobby}")
    @SendTo("/topic/start/game/{lobby}")
    public StartGameResponse startGame(@DestinationVariable String lobby){
        System.out.println("game is starting: " + lobby);

        StartGameResponse startGameResponse = new StartGameResponse();

        GameLobby gameLobby = gameService.fetchGameLobbyFromLobbyCode(lobby);

        Optional<CardPackageModel> byId = cardService.findById(1);

        CardPackageModel cardPackageModel = byId.orElse(null);

        startGameResponse.setCardPackage(cardPackageModel);

        gameLobby.setTotalRounds(cardPackageModel.getDilemmaModels().size());

        int currentRound = gameLobby.getCurrentRound();

        if (currentRound == -1){
            gameLobby.advanceGame();
        } else {
            gameLobby.setCurrentRound(0);
        }

        startGameResponse.setGameLobby(gameLobby);
        startGameResponse.setCurrentRound(gameLobby.getCurrentRound());
        startGameResponse.setMessage("Game is starting");

        return startGameResponse;
    }

    @MessageMapping("/game/next-card/{lobby}/{nextCard}")
    @SendTo("/topic/next-card/{lobby}")
    public NextCardResponse nextCard(@DestinationVariable String lobby, @DestinationVariable int nextCard){
        NextCardResponse nextCardResponse = new NextCardResponse();
        GameLobby gameLobby = gameService.fetchGameLobbyFromLobbyCode(lobby);

        int totalRounds = gameLobby.getTotalRounds();


        if (nextCard < 0){
            // zero is first card
            nextCardResponse.setGameIsDone(false);
            nextCardResponse.setMessage("U cant go more backwards");
            nextCardResponse.setCurrentRound(0);

        } else if (nextCard > totalRounds-1){
            // game is done
            nextCardResponse.setGameIsDone(true);
            nextCardResponse.setMessage("Game is done");
            nextCardResponse.setCurrentRound(nextCard);


        } else {
            gameService.setCurrentRound(lobby, nextCard);

            nextCardResponse.setGameIsDone(false);
            nextCardResponse.setCurrentRound(nextCard);
            nextCardResponse.setMessage("Current round is " + nextCard);
        }


        return nextCardResponse;
    }

    @MessageMapping("/game/create/{lobby}")
    @SendTo("/topic/greetings/{lobby}")
    public LobbyResponse createLobby(@DestinationVariable String lobby) throws Exception {

        LobbyResponse lobbyResponse = new LobbyResponse();


        GameLobby gameLobby = gameService.fetchGameLobbyFromLobbyCode(lobby);

        lobbyResponse.setGameLobby(gameLobby);

        lobbyResponse.setMessage("Created lobby: " + lobby);



        return lobbyResponse;
    }


    @MessageMapping("/game/update/value/{lobby}/{answer}")
    @SendTo("/topic/answer/value/{lobby}")
    public String updateRangeValue(@DestinationVariable String lobby, @DestinationVariable String answer){
        return answer;
    }

    @MessageMapping("/game/update/importance/{lobby}/{answer}")
    @SendTo("/topic/answer/importance/{lobby}")
    public String updateRangeImportance(@DestinationVariable String lobby, @DestinationVariable String answer){
        return answer;
    }


}