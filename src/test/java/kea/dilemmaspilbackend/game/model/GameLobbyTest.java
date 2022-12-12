package kea.dilemmaspilbackend.game.model;

import kea.dilemmaspilbackend.game.model.GameLobby;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameLobbyTest {
    GameLobby gameLobby;

    @BeforeEach // kaldes før hver @Test
    public void setUp()  {
        gameLobby = new GameLobby();
    }

    @Test
    public void testCreateLobbyCode(){
        Assertions.assertEquals(gameLobby.getLobbyCode().length(), 5);
    }
}
