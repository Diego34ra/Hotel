package br.edu.ifgoiano.hotel.controller;

import br.edu.ifgoiano.hotel.controller.dto.mapper.MyModelMapper;
import br.edu.ifgoiano.hotel.controller.dto.request.RoomOutputDTO;
import br.edu.ifgoiano.hotel.model.Room;
import br.edu.ifgoiano.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/hotel/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private MyModelMapper mapper;

    @PostMapping
    public ResponseEntity<Room> create(@RequestBody Room room){
        var roomCreated = roomService.create(room);
        return ResponseEntity.status(HttpStatus.CREATED).body(roomCreated);
    }

    @GetMapping
    public ResponseEntity<List<RoomOutputDTO>> findAll(){
        List<RoomOutputDTO> rooms = mapper.toList(roomService.findAll(),RoomOutputDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(rooms);
    }

    @GetMapping("{id}")
    public ResponseEntity<RoomOutputDTO> findById(@PathVariable Long id){
        RoomOutputDTO room = mapper.mapTo(roomService.findById(id),RoomOutputDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(room);
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
