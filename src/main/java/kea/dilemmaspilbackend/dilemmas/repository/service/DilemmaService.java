package kea.dilemmaspilbackend.dilemmas.repository.service;

import kea.dilemmaspilbackend.dilemmas.model.DilemmaModel;
import kea.dilemmaspilbackend.dilemmas.repository.DilemmaRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class DilemmaService implements CrudInterface<DilemmaModel, Integer> {

    private DilemmaRepository dilemmaRepository;

    public DilemmaService(DilemmaRepository dilemmaRepository){
        this.dilemmaRepository = dilemmaRepository;
    }

    @Override
    public Set<DilemmaModel> findAll() {
        Set<DilemmaModel> set = new HashSet<>(dilemmaRepository.findAll());

        return set;
    }

    @Override
    public DilemmaModel save(DilemmaModel object) {
        return dilemmaRepository.save(object);
    }

    @Override
    public void delete(DilemmaModel object) {
        dilemmaRepository.delete(object);
    }

    @Override
    public void deleteById(Integer integer) {
        dilemmaRepository.deleteById(integer);
    }

    @Override
    public Optional<DilemmaModel> findById(Integer integer) {
        return dilemmaRepository.findById(integer);
    }
}
