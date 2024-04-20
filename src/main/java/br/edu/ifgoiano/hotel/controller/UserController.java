package br.edu.ifgoiano.hotel.controller;

import br.edu.ifgoiano.hotel.model.User;
import br.edu.ifgoiano.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private UserService usuarioService;
    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user){
        var response = usuarioService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id, @RequestBody User user){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
