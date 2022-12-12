package kea.dilemmaspilbackend.dilemmas.controller;

import kea.dilemmaspilbackend.dilemmas.model.DilemmaModel;
import kea.dilemmaspilbackend.dilemmas.model.HintsDilemmaModel;
import kea.dilemmaspilbackend.dilemmas.repository.service.DilemmaService;
import kea.dilemmaspilbackend.dilemmas.repository.service.HintsDilemmaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class HintsDilemmaController {

    private HintsDilemmaService hintsDilemmaService;
    private DilemmaService dilemmaService;

    HintsDilemmaController(HintsDilemmaService hintsDilemmaService, DilemmaService dilemmaService){
        this.hintsDilemmaService = hintsDilemmaService;
        this.dilemmaService = dilemmaService;
    }

    // uses the id from the dilemma the hints will be part of
    @PostMapping("/api/post/create/{id}/hintsdilemma")
    public ResponseEntity<String> createHint(@RequestBody HintsDilemmaModel hintsDilemmaModel, @PathVariable Integer id){

        Optional<DilemmaModel> checkModel = dilemmaService.findById(id);

        if(checkModel.isPresent()){

            DilemmaModel model = dilemmaService.findById(id).get();

            model.addHint(hintsDilemmaModel);
            hintsDilemmaModel.setDilemmaModelHI(model);

            hintsDilemmaService.save(hintsDilemmaModel);

            return ResponseEntity.ok("Hints created");
        }
        else{
            return ResponseEntity.ok("Not created");
        }
    }

    // requires to receive id in the body
    @PostMapping("/api/post/update/{id}/hintsdilemma")
    public ResponseEntity<Map> updateHint(@RequestBody HintsDilemmaModel hintsDilemmaModel, @PathVariable() Integer id) {

        Optional<HintsDilemmaModel> oldHint = hintsDilemmaService.findById(id);
        if (oldHint.isPresent() && hintsDilemmaModel.getId() == id) {

            HintsDilemmaModel nextHint = oldHint.get();

            nextHint.setDaForHint(hintsDilemmaModel.getDaForHint());
            nextHint.setDaAgainstHint(hintsDilemmaModel.getDaAgainstHint());
            nextHint.setEnForHint(hintsDilemmaModel.getEnForHint());
            nextHint.setEnAgainstHint(hintsDilemmaModel.getEnAgainstHint());

            hintsDilemmaService.save(nextHint);

            Map<String, String> map = new HashMap<>();
            map.put("Message", "Updated hint");
            return ResponseEntity.ok(map);
        }
        else{
            Map<String, String> map = new HashMap<>();
            map.put("Message", "Did not update hint");
            return ResponseEntity.ok(map);
        }
    }

    // uses hints own id
    @PostMapping("api/post/delete/{id}/hintsdilemma")
    public ResponseEntity<String> deleteHint(@PathVariable() Integer id){

        Optional<HintsDilemmaModel> model = hintsDilemmaService.findById(id);

        if(model.isPresent()) {

            hintsDilemmaService.deleteById(id);

            return ResponseEntity.ok("Deleted hint");
        }
        else{
            return ResponseEntity.ok("Did not find requested hint");
        }
    }

    // uses a dilemmas id to find all coresponding hints
    @GetMapping("/api/get/findall/{id}/hintsdilemma")
    public ResponseEntity<Set<HintsDilemmaModel>> findAllHints(@PathVariable Integer id){

        Set<HintsDilemmaModel> models =  hintsDilemmaService.findAll();
        Set<HintsDilemmaModel> specificDilemmaList = new HashSet<>();

        Iterator<HintsDilemmaModel> iterator = models.iterator();

        while(iterator.hasNext()){

            HintsDilemmaModel checkHint = iterator.next();

            if(checkHint.getDilemmaModelHI().getId() == id){

                specificDilemmaList.add(checkHint);
            }
        }

        return ResponseEntity.ok(specificDilemmaList);
    }


}
