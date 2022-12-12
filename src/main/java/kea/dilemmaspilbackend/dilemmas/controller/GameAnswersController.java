package kea.dilemmaspilbackend.dilemmas.controller;

import kea.dilemmaspilbackend.dilemmas.model.DilemmaModel;
import kea.dilemmaspilbackend.dilemmas.model.GameAnswersModel;
import kea.dilemmaspilbackend.dilemmas.repository.service.DilemmaService;
import kea.dilemmaspilbackend.dilemmas.repository.service.GameAnswersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

@RestController
public class GameAnswersController {

    private GameAnswersService gameAnswersService;
    private DilemmaService dilemmaService;

    GameAnswersController(GameAnswersService gameAnswersService, DilemmaService dilemmaService){
        this.dilemmaService = dilemmaService;
        this.gameAnswersService = gameAnswersService;
    }

    // uses the id from the relevant dilemma
    @PostMapping("/api/post/create/{id}/gameanswers")
    public ResponseEntity<String> createComment(@RequestBody GameAnswersModel gameAnswersModel, @PathVariable() Integer id){

        Optional<DilemmaModel> checkModel = dilemmaService.findById(id);

        if(checkModel.isPresent()){

            DilemmaModel model = dilemmaService.findById(id).get();

            model.addGameAnswers(gameAnswersModel);
            gameAnswersModel.setDilemmaModelGA(model);

            gameAnswersService.save(gameAnswersModel);

            String msg = "Saved answers with dilemma difficulty of " + gameAnswersModel.getDilemmaDifficulty()
                    + " and discussion rating of " + gameAnswersModel.getDiscussionQuality();

            return ResponseEntity.ok(msg);
        }
        else{
            return ResponseEntity.ok("Not created");
        }
    }
    // uses the id from corresponding dilemma
    @GetMapping("/api/get/findall/{id}/commentsdilemma")
    public ResponseEntity<Set<GameAnswersModel>> findAllForGameAnswers(@PathVariable() Integer id){

        Set<GameAnswersModel> all =  gameAnswersService.findAll();
        Set<GameAnswersModel> specificAnswerList = new HashSet<>();

        Iterator<GameAnswersModel> iterator = all.iterator();

        while(iterator.hasNext()){

            GameAnswersModel checkComment = iterator.next();

            if(checkComment.getDilemmaModelGA().getId() == id){

                specificAnswerList.add(checkComment);
            }
        }
        return ResponseEntity.ok(specificAnswerList);
    }
}
