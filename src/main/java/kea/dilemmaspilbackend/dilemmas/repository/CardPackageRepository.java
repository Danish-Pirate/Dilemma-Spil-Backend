package kea.dilemmaspilbackend.dilemmas.repository;

import kea.dilemmaspilbackend.dilemmas.model.CardPackageModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface CardPackageRepository extends JpaRepository<CardPackageModel, Integer> {

    List<CardPackageModel> findCardPackageModelsByDilemmaModelsId(Integer dilemmaId);
}
