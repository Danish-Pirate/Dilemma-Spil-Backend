package kea.dilemmaspilbackend.dilemmas.controller;

import kea.dilemmaspilbackend.dilemmas.model.CommentsDilemmaModel;
import kea.dilemmaspilbackend.dilemmas.model.DilemmaModel;
import kea.dilemmaspilbackend.dilemmas.repository.service.CommentsDilemmaService;
import kea.dilemmaspilbackend.dilemmas.repository.service.DilemmaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
@CrossOrigin
@RestController
public class CommentsDilemmaController {

    private CommentsDilemmaService commentsDilemmaService;
    private DilemmaService dilemmaService;

    CommentsDilemmaController(CommentsDilemmaService commentsDilemmaService, DilemmaService dilemmaService){
        this.commentsDilemmaService = commentsDilemmaService;
        this.dilemmaService = dilemmaService;
    }

    // uses the id from the relevant dilemma
    @PostMapping("/api/post/create/{id}/commentsdilemma")
    public ResponseEntity<String> createComment(@RequestBody CommentsDilemmaModel commentsDilemmaModel, @PathVariable() Integer id){

        Optional<DilemmaModel> checkModel = dilemmaService.findById(id);

        if(checkModel.isPresent()){

        DilemmaModel model = dilemmaService.findById(id).get();

        model.addComment(commentsDilemmaModel);
        commentsDilemmaModel.setDilemmaModelCP(model);

        commentsDilemmaService.save(commentsDilemmaModel);

        String msg = "Saved the comment: " + commentsDilemmaModel.getComments();

        return ResponseEntity.ok(msg);
        }
        else{
            return ResponseEntity.ok("Not created");
        }
    }

    // uses the comments id
    @PostMapping("/api/post/delete/{id}/commentsdilemma")
    public ResponseEntity<String> deleteComment(@PathVariable() Integer id){

        Optional<CommentsDilemmaModel> checkModel = commentsDilemmaService.findById(id);

        if(checkModel.isPresent()) {
            commentsDilemmaService.deleteById(id);


            return ResponseEntity.ok("Comment deleted");
        }
        else{
            return ResponseEntity.ok("Comment doesnt exist");
        }
    }

    // uses a dilemmas id to find all comments for that dilemma
    @GetMapping("/api/get/findallfordilemma/{id}/commentsdilemma")
    public ResponseEntity<Set<CommentsDilemmaModel>> findAllForDilemma(@PathVariable() Integer id){

       Set<CommentsDilemmaModel> all =  commentsDilemmaService.findAll();
       Set<CommentsDilemmaModel> specificDilemmaList = new HashSet<>();

       Iterator<CommentsDilemmaModel> iterator = all.iterator();

        while(iterator.hasNext()){

            CommentsDilemmaModel checkComment = iterator.next();

            if(checkComment.getDilemmaModelCP().getId() == id){

                specificDilemmaList.add(checkComment);

            }
        }
       return ResponseEntity.ok(specificDilemmaList);
    }

}
