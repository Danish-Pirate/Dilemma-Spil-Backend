package kea.dilemmaspilbackend.dilemmas.repository;

import kea.dilemmaspilbackend.dilemmas.model.HintsDilemmaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HintsDilemmaRepository extends JpaRepository<HintsDilemmaModel, Integer> {
}
