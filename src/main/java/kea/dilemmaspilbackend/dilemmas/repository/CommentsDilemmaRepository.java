package kea.dilemmaspilbackend.dilemmas.repository;

import kea.dilemmaspilbackend.dilemmas.model.CommentsDilemmaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsDilemmaRepository extends JpaRepository<CommentsDilemmaModel, Integer> {
}
