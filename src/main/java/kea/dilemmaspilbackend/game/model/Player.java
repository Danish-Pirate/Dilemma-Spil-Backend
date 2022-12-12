package kea.dilemmaspilbackend.game.model;

import kea.dilemmaspilbackend.dilemmas.model.DilemmaModel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Player {
    private String name;
    private boolean isReady;
    private List<RoundResponse> listOfRoundResponses;
    private StudyField groupFieldOfStudy;

    public Player() {
        this.listOfRoundResponses = new ArrayList<>();
    }

    public void chooseResponse(DilemmaModel dilemma, int importanceOfDilemma, int valueOfDiscussion) {
        listOfRoundResponses.add(new RoundResponse(dilemma, importanceOfDilemma, valueOfDiscussion));
    }
}
