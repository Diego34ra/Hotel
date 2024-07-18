package br.edu.ifgoiano.hotel.controller;

import br.edu.ifgoiano.hotel.model.Convenience;
import br.edu.ifgoiano.hotel.service.ConvenienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/hotel/convenience")
public class ConvenienceController {

    @Autowired
    private ConvenienceService convenienceService;

    @PostMapping
    public ResponseEntity<Convenience> create(@RequestBody Convenience convenience){
        return ResponseEntity.status(HttpStatus.CREATED).body(convenience);
    }

    @GetMapping
    @Cacheable("cacheConveniences")
    public ResponseEntity<List<Convenience>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(convenienceService.findAll());
    }

    @PutMapping("{id}")
    public ResponseEntity<Convenience> update(@PathVariable Long id,@RequestBody Convenience convenience){
        Convenience convenienceUpdated = convenienceService.update(id,convenience);
        return ResponseEntity.status(HttpStatus.OK).body(convenienceUpdated);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        convenienceService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
