package br.edu.ifgoiano.hotel.controller;

import br.edu.ifgoiano.hotel.model.Room;
import br.edu.ifgoiano.hotel.model.User;
import br.edu.ifgoiano.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/room")
public class RoomController {

    @Autowired
    private RoomService quartoService;

    @PostMapping
    public ResponseEntity<Room> create(@RequestBody Room room){
        var response = quartoService.create(room);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<Room>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(quartoService.findAll());
    }


}
