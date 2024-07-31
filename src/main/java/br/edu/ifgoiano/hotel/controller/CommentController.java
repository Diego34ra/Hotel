package br.edu.ifgoiano.hotel.controller;

import br.edu.ifgoiano.hotel.controller.dto.mapper.MyModelMapper;
import br.edu.ifgoiano.hotel.controller.dto.request.CommentInputDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.CommentOutputDTO;
import br.edu.ifgoiano.hotel.controller.exception.ErrorDetails;
import br.edu.ifgoiano.hotel.service.CommentService;
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
@RequestMapping("api/v1/hotel/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private MyModelMapper mapper;

    @PostMapping
    @Operation(summary = "Criar um comentário")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Comentário criado com sucesso.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CommentOutputDTO.class))})
    })
    public ResponseEntity<CommentOutputDTO> create(@RequestBody CommentInputDTO comment, @RequestParam Long clientId, @RequestParam Long roomId){
        var commentCreated = commentService.create(comment,clientId,roomId);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentCreated);
    }

    @GetMapping("/room/{roomId}")
    @Operation(summary = "Buscar comentários pelo id do quarto.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Comentários buscadas com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CommentOutputDTO.class))
                    )
            )
    })
    public ResponseEntity<List<CommentOutputDTO>> findAll(@PathVariable Long roomId){
        List<CommentOutputDTO> commentOutputDTOS = commentService.findAllByRoomId(roomId);
        return ResponseEntity.status(HttpStatus.OK).body(commentOutputDTOS);
    }

    @PutMapping("{id}")
    @Operation(summary = "Atualizar um comentário")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Comentário atualizado com sucesso.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CommentOutputDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Comentário não encontrado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public ResponseEntity<CommentOutputDTO> update(@PathVariable Long id,@RequestBody CommentInputDTO comment){
        var commentUpdated = commentService.update(id,comment);
        return ResponseEntity.status(HttpStatus.OK).body(commentUpdated);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Deletar um comentário")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Comentário deletado com sucesso.")
    })
    public ResponseEntity<?> delete(@PathVariable Long id){
        commentService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
