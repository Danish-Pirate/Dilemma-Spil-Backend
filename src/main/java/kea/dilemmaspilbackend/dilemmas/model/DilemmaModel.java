package kea.dilemmaspilbackend.dilemmas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class DilemmaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String daName;
    private String enName;
    private String daDescription;
    private String enDescription;

    @OneToMany(mappedBy = "dilemmaModelCP", cascade = CascadeType.ALL)
    private Set<CommentsDilemmaModel> commentsDilemmaModels = new HashSet<>();

    @OneToMany(mappedBy = "dilemmaModelGA", cascade = CascadeType.ALL)
    private Set<GameAnswersModel> gameAnswersModels = new HashSet<>();

    @OneToMany(mappedBy = "dilemmaModelHI", cascade = CascadeType.ALL)
    private Set<HintsDilemmaModel> hintsDilemmaModels = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.ALL},
            mappedBy = "dilemmaModels")
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Set<CardPackageModel> cardPackageModels = new HashSet<>();

    public void addComment(CommentsDilemmaModel commentsDilemmaModel){
                this.commentsDilemmaModels.add(commentsDilemmaModel);
    }
    public void addHint(HintsDilemmaModel hintsDilemmaModel){
        this.hintsDilemmaModels.add(hintsDilemmaModel);
    }
    public void addGameAnswers(GameAnswersModel gameAnswersModel){
        this.gameAnswersModels.add(gameAnswersModel);
    }

    public void addCardPackage(CardPackageModel cardPackageModel){
        this.cardPackageModels.add(cardPackageModel);
    }
}




