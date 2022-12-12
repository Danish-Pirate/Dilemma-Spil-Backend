package kea.dilemmaspilbackend.game.model;

import kea.dilemmaspilbackend.dilemmas.model.DilemmaModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class RoundResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private DilemmaModel dilemma;
    private int importanceOfDilemma;
    private int valueOfDiscussion;

    public RoundResponse(DilemmaModel dilemma, int importanceOfDilemma, int valueOfDiscussion) {
        this.dilemma = dilemma;
        this.importanceOfDilemma = importanceOfDilemma;
        this.valueOfDiscussion = valueOfDiscussion;
    }
}
