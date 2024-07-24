package br.edu.ifgoiano.hotel.controller;

import br.edu.ifgoiano.hotel.controller.dto.request.userDTOs.UserDetailOutputDTO;
import br.edu.ifgoiano.hotel.controller.dto.mapper.MyModelMapper;
import br.edu.ifgoiano.hotel.controller.dto.request.userDTOs.UserOutputDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.userDTOs.UserSimpleOutputDTO;
import br.edu.ifgoiano.hotel.model.User;
import br.edu.ifgoiano.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/hotel/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MyModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<UserOutputDTO> create(@RequestBody User user){
        UserOutputDTO userOutput = modelMapper.mapObject(userService.create(user), UserOutputDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(userOutput);
    }

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<List<UserSimpleOutputDTO>> findAll(){
        List<UserSimpleOutputDTO> userOutputList = modelMapper.mapList(userService.findAll(), UserSimpleOutputDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(userOutputList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailOutputDTO> findById(@PathVariable Long id){
        UserDetailOutputDTO userOutput = modelMapper.mapObject(userService.findById(id), UserDetailOutputDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(userOutput);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserOutputDTO> update(@PathVariable Long id, @RequestBody User user){
        UserOutputDTO userOutput = modelMapper.mapObject(userService.update(id,user), UserOutputDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(userOutput);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
