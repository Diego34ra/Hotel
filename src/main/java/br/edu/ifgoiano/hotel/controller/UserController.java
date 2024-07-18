package br.edu.ifgoiano.hotel.controller;

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
    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user){
        var response = userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user){
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(id,user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
