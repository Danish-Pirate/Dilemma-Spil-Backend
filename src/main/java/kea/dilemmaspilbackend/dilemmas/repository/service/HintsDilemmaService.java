package kea.dilemmaspilbackend.dilemmas.repository.service;

import kea.dilemmaspilbackend.dilemmas.model.HintsDilemmaModel;
import kea.dilemmaspilbackend.dilemmas.repository.HintsDilemmaRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class HintsDilemmaService implements CrudInterface<HintsDilemmaModel, Integer> {

    private HintsDilemmaRepository hintsDilemmaRepository;

    HintsDilemmaService(HintsDilemmaRepository hintsDilemmaRepository){
        this.hintsDilemmaRepository = hintsDilemmaRepository;
    }

    @Override
    public Set<HintsDilemmaModel> findAll() {
        Set<HintsDilemmaModel> set = new HashSet<>(hintsDilemmaRepository.findAll());
        return set;
    }

    @Override
    public HintsDilemmaModel save(HintsDilemmaModel object) {
        return hintsDilemmaRepository.save(object);
    }

    @Override
    public void delete(HintsDilemmaModel object) {
        hintsDilemmaRepository.delete(object);
    }

    @Override
    public void deleteById(Integer integer) {
        hintsDilemmaRepository.deleteById(integer);
    }

    @Override
    public Optional<HintsDilemmaModel> findById(Integer integer) {
        return hintsDilemmaRepository.findById(integer);
    }
}
