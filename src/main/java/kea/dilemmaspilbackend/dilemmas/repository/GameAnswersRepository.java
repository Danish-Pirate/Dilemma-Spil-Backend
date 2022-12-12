package kea.dilemmaspilbackend.dilemmas.repository;

import kea.dilemmaspilbackend.dilemmas.model.GameAnswersModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameAnswersRepository  extends JpaRepository<GameAnswersModel, Integer> {
}
