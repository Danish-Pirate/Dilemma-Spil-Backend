package kea.dilemmaspilbackend.game.model.persistmodel;

import kea.dilemmaspilbackend.game.model.RoundResponse;
import kea.dilemmaspilbackend.game.model.StudyField;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class PlayerPersist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    private List<RoundResponse> listOfRoundResponses;
    private StudyField groupFieldOfStudy;

    public PlayerPersist(List<RoundResponse> listOfRoundResponses, StudyField groupFieldOfStudy) {
        this.listOfRoundResponses = listOfRoundResponses;
        this.groupFieldOfStudy = groupFieldOfStudy;
    }
}
