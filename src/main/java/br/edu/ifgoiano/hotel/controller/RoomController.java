package br.edu.ifgoiano.hotel.controller;

import br.edu.ifgoiano.hotel.controller.dto.mapper.MyModelMapper;
import br.edu.ifgoiano.hotel.controller.dto.request.*;
import br.edu.ifgoiano.hotel.controller.dto.request.roomDTO.RoomInputDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.roomDTO.RoomNoCommentOutputDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.roomDTO.RoomOutputDTO;
import br.edu.ifgoiano.hotel.controller.exception.ErrorDetails;
import br.edu.ifgoiano.hotel.model.FileResponse;
import br.edu.ifgoiano.hotel.model.FileResponseDownload;
import br.edu.ifgoiano.hotel.model.Room;
import br.edu.ifgoiano.hotel.model.RoomType;
import br.edu.ifgoiano.hotel.service.FileStorageService;
import br.edu.ifgoiano.hotel.service.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/hotel/rooms")
@Tag(name = "Room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private MyModelMapper mapper;

    @PostMapping
    @Operation(summary = "Criar um quarto")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Quarto criado com sucesso.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = RoomOutputDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Acesso negado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public ResponseEntity<RoomOutputDTO> create(@RequestBody RoomInputDTO room){
        var roomCreated = roomService.create(room);
        return ResponseEntity.status(HttpStatus.CREATED).body(roomCreated);
    }

    @PostMapping(value = "{roomId}/upload-photo", consumes = "multipart/form-data")
    @Operation(summary = "Enviar foto de um quarto")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Foto salva com sucesso.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Acesso negado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public ResponseEntity<FileResponse> uploadPhoto(@PathVariable Long roomId, @RequestParam("photo") MultipartFile photo) {
        var message = fileStorageService.saveFile(roomId, photo);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping("/{roomId}/download-photo/{filename:.+}")
    @Operation(summary = "Baixar de uma foto específico")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Download iniciado com sucesso.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Resource.class))}),
            @ApiResponse(responseCode = "401", description = "Acesso negado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public ResponseEntity<Resource> downloadPhoto(
            @PathVariable Long roomId,
            @PathVariable String filename) {
        var responseResource = fileStorageService.downloadFile(roomId,filename);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(responseResource.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + responseResource.getResource().getFilename() + "\"")
                .body(responseResource.getResource());
    }

    @GetMapping("/{roomId}/photo")
    @Operation(summary = "Busca as fotos de um quarto")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Fotos do Quarto buscada com sucesso.",
                    content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = FileResponse.class))
                            )
                    ),
            @ApiResponse(responseCode = "401", description = "Acesso negado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public ResponseEntity<List<FileResponse>> listAllPhotos(@PathVariable Long roomId) {
        List<FileResponse> fileResponses = fileStorageService.downloadAllPhotos(roomId);

        return ResponseEntity.ok(fileResponses);
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
            ),
            @ApiResponse(responseCode = "401", description = "Acesso negado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public ResponseEntity<List<RoomNoCommentOutputDTO>> findAll(
            @RequestParam(required = false) RoomType type,
            @RequestParam(required = false) Integer capacity,
            @RequestParam(required = false) Boolean ascending,
            @RequestParam(required = false) Boolean available){
        List<RoomNoCommentOutputDTO> rooms = roomService.findRooms(type, capacity, ascending, available);
        return ResponseEntity.status(HttpStatus.OK).body(rooms);
    }

    @GetMapping("{id}")
    @Operation(summary = "Busca um quarto pelo id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Quarto encontrado com sucesso.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = RoomOutputDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Acesso negado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))}),
            @ApiResponse(responseCode = "404", description = "Quarto não encontrado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public ResponseEntity<RoomOutputDTO> findById(@PathVariable Long id){
        RoomOutputDTO room = roomService.findOneById(id);
        return ResponseEntity.status(HttpStatus.OK).body(room);
    }

    @PutMapping("{id}")
    @Operation(summary = "Atualizar um quarto")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Quarto atualizado com sucesso.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = RoomOutputDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Acesso negado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))}),
            @ApiResponse(responseCode = "404", description = "Quarto não encontrado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public ResponseEntity<RoomOutputDTO> update(@PathVariable Long id, @RequestBody Room room){
        return ResponseEntity.status(HttpStatus.OK).body(roomService.update(id,room));
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Deletar um quarto")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Quarto deletado com sucesso.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Acesso negado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public ResponseEntity<?> delete(@PathVariable Long id){
        roomService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
