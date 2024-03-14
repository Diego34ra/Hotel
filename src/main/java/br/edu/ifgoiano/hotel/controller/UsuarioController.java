package br.edu.ifgoiano.hotel.controller;

import br.edu.ifgoiano.hotel.model.Usuario;
import br.edu.ifgoiano.hotel.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario){
        var response = usuarioService.create(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id, @RequestBody Usuario usuario){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
