package kea.dilemmaspilbackend.dilemmas.service;

import kea.dilemmaspilbackend.dilemmas.model.GameAnswersModel;
import kea.dilemmaspilbackend.dilemmas.repository.GameAnswersRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class GameAnswersService implements CrudInterface<GameAnswersModel, Integer> {

    private final GameAnswersRepository gameAnswersRepository;

    GameAnswersService(GameAnswersRepository gameAnswersRepository){
        this.gameAnswersRepository = gameAnswersRepository;
    }

    @Override
    public Set<GameAnswersModel> findAll() {
        Set<GameAnswersModel> set = new HashSet<>(gameAnswersRepository.findAll());
        return set;
    }

    @Override
    public GameAnswersModel save(GameAnswersModel object) {
        return gameAnswersRepository.save(object);
    }

    @Override
    public void delete(GameAnswersModel object) {
        gameAnswersRepository.delete(object);
    }

    @Override
    public void deleteById(Integer integer) {
        gameAnswersRepository.deleteById(integer);
    }

    @Override
    public Optional<GameAnswersModel> findById(Integer integer) {
        return gameAnswersRepository.findById(integer);
    }

    public void saveAnswers(List<GameAnswersModel> list) {
        for(GameAnswersModel gameAnswersModel: list){
            save(gameAnswersModel);
        }
    }


}
