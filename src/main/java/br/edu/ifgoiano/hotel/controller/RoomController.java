package br.edu.ifgoiano.hotel.controller;

import br.edu.ifgoiano.hotel.model.Room;
import br.edu.ifgoiano.hotel.model.User;
import br.edu.ifgoiano.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping
    public ResponseEntity<Room> create(@RequestBody Room room){
        var response = roomService.create(room);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Cacheable("cacheRooms")
    public ResponseEntity<List<Room>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(roomService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Room> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(roomService.findById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<Room> update(@PathVariable Long id, @RequestBody Room room){
        return ResponseEntity.status(HttpStatus.OK).body(roomService.update(id,room));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        roomService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
