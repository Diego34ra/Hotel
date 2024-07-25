package br.edu.ifgoiano.hotel.controller;

import br.edu.ifgoiano.hotel.controller.dto.request.UserOutputDTO;
import br.edu.ifgoiano.hotel.controller.exception.ErrorDetails;
import br.edu.ifgoiano.hotel.model.User;
import br.edu.ifgoiano.hotel.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Criar um usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = UserOutputDTO.class))})
    })
    public ResponseEntity<UserOutputDTO> create(@RequestBody User user){
        var response = userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(summary = "Buscar todos os usuários")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuários buscados com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UserOutputDTO.class))
                    )
            )
    })
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um usuário pelo id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = UserOutputDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public ResponseEntity<UserOutputDTO> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = UserOutputDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Usário não encontrado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public ResponseEntity<UserOutputDTO> update(@PathVariable Long id, @RequestBody User user){
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(id,user));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso.")
    })
    public ResponseEntity<?> delete(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
