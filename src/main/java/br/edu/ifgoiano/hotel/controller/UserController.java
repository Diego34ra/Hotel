package br.edu.ifgoiano.hotel.controller;


import br.edu.ifgoiano.hotel.controller.dto.request.userDTO.UserInputDTO;
import br.edu.ifgoiano.hotel.controller.exception.ErrorDetails;
import br.edu.ifgoiano.hotel.controller.dto.request.userDTO.UserDetailOutputDTO;
import br.edu.ifgoiano.hotel.controller.dto.mapper.MyModelMapper;
import br.edu.ifgoiano.hotel.controller.dto.request.userDTO.UserSimpleOutputDTO;
import br.edu.ifgoiano.hotel.model.User;
import br.edu.ifgoiano.hotel.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/hotel/users")
@Tag(name = "User")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MyModelMapper modelMapper;

    @PostMapping
    @Order(1)
    @Operation(summary = "Criar um usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = UserDetailOutputDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Acesso negado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public ResponseEntity<UserDetailOutputDTO> create(@RequestBody @Valid UserInputDTO user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(user));
    }

    @GetMapping
    @Order(2)
    @Operation(summary = "Buscar todos os usuários")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuários buscados com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UserSimpleOutputDTO.class))
                    )
            ),
            @ApiResponse(responseCode = "401", description = "Acesso negado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public ResponseEntity<List<UserSimpleOutputDTO>> findAll(){
        List<UserSimpleOutputDTO> userOutputList = modelMapper.toList(userService.findAll(), UserSimpleOutputDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(userOutputList);
    }

    @GetMapping("/{id}")
    @Order(3)
    @Operation(summary = "Busca um usuário pelo id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = UserDetailOutputDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Acesso negado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))}),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public ResponseEntity<UserDetailOutputDTO> findById(@PathVariable Long id){
        UserDetailOutputDTO userOutput = modelMapper.mapTo(userService.findById(id), UserDetailOutputDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(userOutput);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = UserDetailOutputDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Acesso negado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))}),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public ResponseEntity<UserDetailOutputDTO> update(@PathVariable Long id, @RequestBody User user){
        UserDetailOutputDTO userOutput = modelMapper.mapTo(userService.update(id,user), UserDetailOutputDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(userOutput);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso.",content = @Content),
            @ApiResponse(responseCode = "401", description = "Acesso negado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public ResponseEntity<?> delete(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
