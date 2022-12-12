package kea.dilemmaspilbackend.dilemmas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
public class CommentsDilemmaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String comments;

    @ManyToOne
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    private DilemmaModel dilemmaModelCP;
}
