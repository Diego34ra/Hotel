package br.edu.ifgoiano.hotel.controller;

import br.edu.ifgoiano.hotel.model.Hospitality;
import br.edu.ifgoiano.hotel.service.HospitalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/hotel/hospitality")
public class HospitalityController {

    @Autowired
    private HospitalityService hospitalityService;

    @PostMapping
    public ResponseEntity<Hospitality> create(@RequestBody Hospitality hospitality){
        var hospitalityCreated = hospitalityService.create(hospitality);
        return ResponseEntity.status(HttpStatus.CREATED).body(hospitalityCreated);
    }

    @GetMapping
    public ResponseEntity<List<Hospitality>> findAll(){
        var hospitalities = hospitalityService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(hospitalities);
    }

    @PutMapping("{id}")
    public ResponseEntity<Hospitality> update(@PathVariable Long id, @RequestBody Hospitality hospitality){
        var hospitalityUpdated = hospitalityService.update(id, hospitality);
        return ResponseEntity.status(HttpStatus.OK).body(hospitalityUpdated);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        hospitalityService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
