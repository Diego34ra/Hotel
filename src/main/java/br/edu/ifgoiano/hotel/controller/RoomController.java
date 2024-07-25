package br.edu.ifgoiano.hotel.controller;

import br.edu.ifgoiano.hotel.controller.dto.mapper.MyModelMapper;
import br.edu.ifgoiano.hotel.controller.dto.request.RoomOutputDTO;
import br.edu.ifgoiano.hotel.controller.exception.ErrorDetails;
import br.edu.ifgoiano.hotel.model.Room;
import br.edu.ifgoiano.hotel.service.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Criar um quarto")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Quarto criado com sucesso.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = RoomOutputDTO.class))})
    })
    public ResponseEntity<RoomOutputDTO> create(@RequestBody Room room){
        var roomCreated = roomService.create(room);
        return ResponseEntity.status(HttpStatus.CREATED).body(roomCreated);
    }

    @GetMapping
    @Operation(summary = "Buscar todos os quartos")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Quartos buscados com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = RoomOutputDTO.class))
                    )
            )
    })
    public ResponseEntity<List<RoomOutputDTO>> findAll(){
        List<RoomOutputDTO> rooms = roomService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(rooms);
    }

    @GetMapping("{id}")
    @Operation(summary = "Busca um quarto pelo id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Quarto encontrado com sucesso.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = RoomOutputDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Quarto não encontrado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public ResponseEntity<RoomOutputDTO> findById(@PathVariable Long id){
        RoomOutputDTO room = roomService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(room);
    }

    @PutMapping("{id}")
    @Operation(summary = "Atualizar um quarto")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Quarto atualizado com sucesso.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = RoomOutputDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Quarto não encontrado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public ResponseEntity<RoomOutputDTO> update(@PathVariable Long id, @RequestBody Room room){
        return ResponseEntity.status(HttpStatus.OK).body(roomService.update(id,room));
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Deletar um quarto")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Quarto deletado com sucesso.")
    })
    public ResponseEntity<?> delete(@PathVariable Long id){
        roomService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
