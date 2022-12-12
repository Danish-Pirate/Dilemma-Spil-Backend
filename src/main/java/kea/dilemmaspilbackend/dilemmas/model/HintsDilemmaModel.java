package kea.dilemmaspilbackend.dilemmas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
public class HintsDilemmaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String daForHint;
    private String enForHint;
    private String daAgainstHint;
    private String enAgainstHint;

    @ManyToOne
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    private DilemmaModel dilemmaModelHI;

}
