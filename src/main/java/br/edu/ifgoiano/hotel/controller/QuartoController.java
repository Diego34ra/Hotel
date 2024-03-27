package br.edu.ifgoiano.hotel.controller;

import br.edu.ifgoiano.hotel.model.Room;
import br.edu.ifgoiano.hotel.service.QuartoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/quarto")
public class QuartoController {

    @Autowired
    private QuartoService quartoService;

    @GetMapping
    public ResponseEntity<List<Room>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(quartoService.findAll());
    }
}
