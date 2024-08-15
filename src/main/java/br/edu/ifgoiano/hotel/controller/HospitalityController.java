package br.edu.ifgoiano.hotel.controller;


import br.edu.ifgoiano.hotel.controller.exception.ErrorDetails;
import br.edu.ifgoiano.hotel.model.Hospitality;
import br.edu.ifgoiano.hotel.service.HospitalityService;
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
@RequestMapping("api/v1/hotel/hospitality")
@Tag(name = "Hospitality")
public class HospitalityController {

    @Autowired
    private HospitalityService hospitalityService;

    @PostMapping
    @Operation(summary = "Criar uma hospitalidade")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Hospitalidade criada com sucesso.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Hospitality.class))}),
            @ApiResponse(responseCode = "401", description = "Acesso negado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public ResponseEntity<Hospitality> create(@RequestBody Hospitality hospitality){
        var hospitalityCreated = hospitalityService.create(hospitality);
        return ResponseEntity.status(HttpStatus.CREATED).body(hospitalityCreated);
    }

    @GetMapping
    @Operation(summary = "Buscar todas as hospitalidades")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Hospitalidades buscadas com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Hospitality.class))
                    )
            ),
            @ApiResponse(responseCode = "401", description = "Acesso negado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public ResponseEntity<List<Hospitality>> findAll(){
        var hospitalities = hospitalityService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(hospitalities);
    }

    @PutMapping("{id}")
    @Operation(summary = "Atualizar uma a hospitalidade")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Hospitalidade atualizada com sucesso.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Hospitality.class))}),
            @ApiResponse(responseCode = "401", description = "Acesso negado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public ResponseEntity<Hospitality> update(@PathVariable Long id, @RequestBody Hospitality hospitality){
        var hospitalityUpdated = hospitalityService.update(id, hospitality);
        return ResponseEntity.status(HttpStatus.OK).body(hospitalityUpdated);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Deletar uma hospitalidade")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Hospitalidade deletado com sucesso.",content = @Content),
            @ApiResponse(responseCode = "401", description = "Acesso negado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public ResponseEntity<?> delete(@PathVariable Long id){
        hospitalityService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
