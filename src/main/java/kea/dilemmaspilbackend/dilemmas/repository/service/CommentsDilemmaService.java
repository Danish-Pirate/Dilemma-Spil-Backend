package kea.dilemmaspilbackend.dilemmas.repository.service;

import kea.dilemmaspilbackend.dilemmas.model.CommentsDilemmaModel;
import kea.dilemmaspilbackend.dilemmas.repository.CommentsDilemmaRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CommentsDilemmaService implements CrudInterface<CommentsDilemmaModel, Integer> {

    private CommentsDilemmaRepository commentsDilemmaRepository;

    CommentsDilemmaService(CommentsDilemmaRepository commentsDilemmaRepository){
        this.commentsDilemmaRepository = commentsDilemmaRepository;
    }

    @Override
    public Set<CommentsDilemmaModel> findAll() {
        Set<CommentsDilemmaModel> set = new HashSet<>(commentsDilemmaRepository.findAll());
        return set;
    }

    @Override
    public CommentsDilemmaModel save(CommentsDilemmaModel object) {
        return commentsDilemmaRepository.save(object);
    }

    @Override
    public void delete(CommentsDilemmaModel object) {
        commentsDilemmaRepository.delete(object);
    }

    @Override
    public void deleteById(Integer integer) {
        commentsDilemmaRepository.deleteById(integer);
    }

    @Override
    public Optional<CommentsDilemmaModel> findById(Integer integer) {
        return commentsDilemmaRepository.findById(integer);
    }
}
