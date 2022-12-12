package kea.dilemmaspilbackend.dilemmas.controller;

import kea.dilemmaspilbackend.dilemmas.model.CardPackageModel;
import kea.dilemmaspilbackend.dilemmas.model.CommentsDilemmaModel;
import kea.dilemmaspilbackend.dilemmas.model.DilemmaModel;
import kea.dilemmaspilbackend.dilemmas.repository.DilemmaRepository;
import kea.dilemmaspilbackend.dilemmas.repository.service.CardPackageService;
import kea.dilemmaspilbackend.dilemmas.repository.service.DilemmaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;

@RestController
public class CardPackageController {

    private CardPackageService cardPackageService;
    private DilemmaService dilemmaService;

    CardPackageController(CardPackageService cardPackageService, DilemmaService dilemmaService){
        this.cardPackageService = cardPackageService;
        this.dilemmaService = dilemmaService;
    }

    @PostMapping("/api/post/create/cardpackage")
    public ResponseEntity<Map> createCardpackage(@RequestBody CardPackageModel cardPackageModel){

        cardPackageService.save(cardPackageModel);

        Map<String, String> msg = new HashMap<>();

        msg.put("Message", "Created new package!");

       return ResponseEntity.ok(msg);
    }

    @PostMapping("/api/post/delete/{id}/cardpackage")
    public ResponseEntity<Map> deleteCardpackage(@PathVariable Integer id){

        cardPackageService.deleteById(id);

        Map<String, String> msg = new HashMap<>();

        msg.put("Message", "Deleted package!");

        return ResponseEntity.ok(msg);
    }

    @PostMapping("/api/post/adddilemma/{dilemmaid}/{id}/cardpackage")
    public ResponseEntity<Map> addDilemma(@PathVariable Integer dilemmaid, @PathVariable Integer id){

        Optional<CardPackageModel> checkModel = cardPackageService.findById(id);
        Optional<DilemmaModel> checkDilemma = dilemmaService.findById(dilemmaid);

        if(checkModel.isPresent() && checkDilemma.isPresent()){

            CardPackageModel cardPackageModel = cardPackageService.findById(id).get();

            DilemmaModel dilemmaModel = dilemmaService.findById(dilemmaid).get();

            if(cardPackageModel.getDilemmaModels().size() > 0) {

                Set<DilemmaModel> models = cardPackageModel.getDilemmaModels();

                models.add(dilemmaModel);

                cardPackageModel.setDilemmaModels(models);
            }
            else{

                cardPackageModel.addDilemma(dilemmaModel);
            }

            cardPackageService.save(cardPackageModel);

            Map<String, String> msg = new HashMap<>();

            msg.put("MSG","Dilemma added");

            return ResponseEntity.ok(msg);
        }
        else{
            Map<String, String> msg = new HashMap<>();

            msg.put("MSG","Dilemma not added");

            return ResponseEntity.ok(msg);
        }
    }

    @PostMapping("/api/post/removedilemma/{dilemmaid}/{id}/cardpackage")
    public ResponseEntity<Map> removeDilemma(@PathVariable Integer dilemmaid, @PathVariable Integer id){

        Optional<CardPackageModel> checkModel = cardPackageService.findById(id);
        Optional<DilemmaModel> checkDilemma = dilemmaService.findById(dilemmaid);

        if(checkModel.isPresent() && checkDilemma.isPresent()){

            CardPackageModel cardPackageModel = cardPackageService.findById(id).get();

            DilemmaModel dilemmaModel = dilemmaService.findById(dilemmaid).get();

                Set<DilemmaModel> models = cardPackageModel.getDilemmaModels();

                models.remove(dilemmaModel);

                cardPackageModel.setDilemmaModels(models);


            cardPackageService.save(cardPackageModel);

            Map<String, String> msg = new HashMap<>();

            msg.put("MSG","Dilemma removed");

            return ResponseEntity.ok(msg);
        }
        else{
            Map<String, String> msg = new HashMap<>();

            msg.put("MSG","Dilemma not removed");

            return ResponseEntity.ok(msg);
        }
    }

    @GetMapping("/api/get/find/{id}/cardpackage")
    public ResponseEntity<CardPackageModel> findCardPackage(@PathVariable() Integer id){

        Optional<CardPackageModel> checkCardPackageModel = cardPackageService.findById(id);

        if(checkCardPackageModel.isPresent()){
            CardPackageModel cardPackageModel = cardPackageService.findById(id).get();

            return ResponseEntity.ok(cardPackageModel);
        }
        else{
            return ResponseEntity.ok(null);
        }
    }

    @GetMapping("/api/get/findall/cardpackage")
    public ResponseEntity<Set<CardPackageModel>> getAllCardpackages(){

        Set<CardPackageModel> set = new HashSet<>();

        cardPackageService.findAll().forEach(set::add);

        return ResponseEntity.ok(set);
    }

    // uses cardpackage id
    @GetMapping("/api/get/alldilemmas/{id}/cardpackage")
    public ResponseEntity<List<DilemmaModel>> allDilemmasFromCardPackage(@PathVariable Integer id){

        Optional<CardPackageModel> checkCardPackageModel = cardPackageService.findById(id);

        if(checkCardPackageModel.isPresent()){

            List<DilemmaModel> dilemmaModels;

            dilemmaModels = cardPackageService.findDilemmasByCardPackageId(id);

            System.out.println(dilemmaModels.size());

            return ResponseEntity.ok(dilemmaModels);
        }
        else{
            return ResponseEntity.ok(null);
        }
    }

}
