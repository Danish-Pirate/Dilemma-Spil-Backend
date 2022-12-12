package kea.dilemmaspilbackend.dilemmas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class CardPackageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String daName;
    private String enName;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.ALL}
            )
    @JoinTable(name = "dilemma_model_card_package_model",
            joinColumns = @JoinColumn(name = "card_package_model_id"),
            inverseJoinColumns = @JoinColumn(name = "dilemma_model_id"))
    @EqualsAndHashCode.Exclude
    private Set<DilemmaModel> dilemmaModels = new HashSet<>();

    public void addDilemma(DilemmaModel dilemmaModel){
        this.dilemmaModels.add(dilemmaModel);
    }

    public void removeDilemma(DilemmaModel dilemmaModel){

        Iterator<DilemmaModel> iterator = dilemmaModels.iterator();

        while(iterator.hasNext()){
            DilemmaModel remove = iterator.next();

            if(remove == dilemmaModel){
                iterator.remove();
            }
        }

    }

}
