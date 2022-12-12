package kea.dilemmaspilbackend.dilemmas.repository.service;

import kea.dilemmaspilbackend.dilemmas.model.CardPackageModel;
import kea.dilemmaspilbackend.dilemmas.model.DilemmaModel;
import kea.dilemmaspilbackend.dilemmas.repository.CardPackageRepository;
import kea.dilemmaspilbackend.dilemmas.repository.DilemmaRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CardPackageService implements CrudInterface<CardPackageModel, Integer>{

    private CardPackageRepository cardPackageRepository;
    private DilemmaRepository dilemmaRepository;

    CardPackageService(CardPackageRepository cardPackageRepository, DilemmaRepository dilemmaRepository){
        this.cardPackageRepository = cardPackageRepository;
        this.dilemmaRepository = dilemmaRepository;
    }

    @Override
    public Set<CardPackageModel> findAll() {
        Set<CardPackageModel> set = new HashSet<>(cardPackageRepository.findAll());
        return set;
    }

    @Override
    public CardPackageModel save(CardPackageModel object) {
        return cardPackageRepository.save(object);
    }

    @Override
    public void delete(CardPackageModel object) {
        cardPackageRepository.delete(object);
    }

    @Override
    public void deleteById(Integer integer) {
        cardPackageRepository.deleteById(integer);
    }

    @Override
    public Optional<CardPackageModel> findById(Integer integer) {
        return cardPackageRepository.findById(integer);
    }

    public List<DilemmaModel> findDilemmasByCardPackageId(Integer integer){

        List<DilemmaModel> dilemmaModels = dilemmaRepository.findDilemmaModelsByCardPackageModelsId(integer);


        return dilemmaModels;
    }
}
