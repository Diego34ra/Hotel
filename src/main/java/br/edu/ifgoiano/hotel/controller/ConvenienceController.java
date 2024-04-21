package br.edu.ifgoiano.hotel.controller;

import br.edu.ifgoiano.hotel.model.Convenience;
import br.edu.ifgoiano.hotel.service.ConvenienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/convenience")
public class ConvenienceController {

    @Autowired
    private ConvenienceService convenienceService;

    public ResponseEntity<Convenience> create(@RequestBody Convenience convenience){
        return ResponseEntity.status(HttpStatus.CREATED).body(convenience);
    }
}
