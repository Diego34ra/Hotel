package br.edu.ifgoiano.hotel.controller;

import br.edu.ifgoiano.hotel.controller.dto.request.ConvenienceOutputDTO;
import br.edu.ifgoiano.hotel.controller.exception.ErrorDetails;
import br.edu.ifgoiano.hotel.model.Convenience;
import br.edu.ifgoiano.hotel.service.ConvenienceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/hotel/conveniences")
@Tag(name = "Convenience")
public class ConvenienceController {

    @Autowired
    private ConvenienceService convenienceService;

    @PostMapping
    @Operation(summary = "Criar uma comodidade")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Comodidade criada com sucesso.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ConvenienceOutputDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Acesso negado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public ResponseEntity<ConvenienceOutputDTO> create(@RequestBody Convenience convenience){
        return ResponseEntity.status(HttpStatus.CREATED).body(convenienceService.create(convenience));
    }

    @GetMapping
    @Operation(summary = "Buscar todas as comodidades")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Comodidades buscadas com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Convenience.class))
                    )
            ),
            @ApiResponse(responseCode = "401", description = "Acesso negado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public ResponseEntity<List<ConvenienceOutputDTO>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(convenienceService.findAll());
    }



    @PutMapping("{id}")
    @Operation(summary = "Atualizar uma comodidade")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Comodidade atualizada com sucesso.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ConvenienceOutputDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Acesso negado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))}),
            @ApiResponse(responseCode = "404", description = "Comodidade não encontrada.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ConvenienceOutputDTO.class))})
    })
    public ResponseEntity<ConvenienceOutputDTO> update(@PathVariable Long id,@RequestBody Convenience convenience){
        ConvenienceOutputDTO convenienceUpdated = convenienceService.update(id,convenience);
        return ResponseEntity.status(HttpStatus.OK).body(convenienceUpdated);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Deletar uma comodidade")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Comodidade deletada com sucesso.",content = @Content),
            @ApiResponse(responseCode = "401", description = "Acesso negado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public ResponseEntity<?> delete(@PathVariable Long id){
        convenienceService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
