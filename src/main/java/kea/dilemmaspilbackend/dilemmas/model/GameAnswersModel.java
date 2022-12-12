package kea.dilemmaspilbackend.dilemmas.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
public class GameAnswersModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int dilemmaDifficulty;
    private int discussionQuality;

    @ManyToOne
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    private DilemmaModel dilemmaModelGA;
}
