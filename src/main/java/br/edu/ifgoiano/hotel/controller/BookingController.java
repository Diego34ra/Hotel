package br.edu.ifgoiano.hotel.controller;

import br.edu.ifgoiano.hotel.controller.dto.request.*;
import br.edu.ifgoiano.hotel.controller.dto.request.bookingDTO.BookingInputDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.bookingDTO.BookingOutputDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.bookingDTO.BookingSimpleOutputDTO;
import br.edu.ifgoiano.hotel.controller.exception.ErrorDetails;
import br.edu.ifgoiano.hotel.service.BookingService;
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
@RequestMapping("api/v1/hotel/bookings")
@Tag(name = "Booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    @Operation(summary = "Criar uma reserva")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Reserva criada com sucesso.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BookingOutputDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Acesso negado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public ResponseEntity<BookingOutputDTO> create(@RequestBody BookingInputDTO booking){
        var bookingCreated = bookingService.create(booking);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingCreated);
    }

    @GetMapping
    @Operation(summary = "Buscar todas as reservas")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Reservas buscadas com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = BookingSimpleOutputDTO.class))
                    )
            ),
            @ApiResponse(responseCode = "401", description = "Acesso negado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public ResponseEntity<List<BookingSimpleOutputDTO>> findAll(){
        var bookings = bookingService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(bookings);
    }

    @GetMapping("{id}")
    @Operation(summary = "Busca uma reserva pelo id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reserva encontrada com sucesso.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BookingOutputDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Acesso negado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))}),
            @ApiResponse(responseCode = "404", description = "Reserva não encontrada.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public ResponseEntity<BookingOutputDTO> findById(@PathVariable Long id){
        BookingOutputDTO room = bookingService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(room);
    }

    @PutMapping("{bookingId}/hospitality")
    @Operation(summary = "Adicionar hospitalidade")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Hospitalidade adicionada com sucesso.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BookingOutputDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Acesso negado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))}),
            @ApiResponse(responseCode = "404", description = "Não foi encontrado todas as reservas informadas.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public ResponseEntity<BookingOutputDTO> addHospitality(@PathVariable Long bookingId,@RequestBody HospitalityInputDTO hospitalityDTO){
        var bookingAddHospitality = bookingService.addhospitality(bookingId,hospitalityDTO.getHospitalityIds());
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingAddHospitality);
    }

    @PutMapping("{bookingId}/checkIn")
    @Operation(summary = "Realizar checkIn")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "CheckIn realizado com sucesso.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BookingOutputDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Acesso negado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))}),
            @ApiResponse(responseCode = "404", description = "Não é possível fazer checkIn de reserva cancelada.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public ResponseEntity<BookingOutputDTO> checkIn(@PathVariable Long bookingId,@RequestParam Long employeeId){
        var bookingCheckIn = bookingService.checkIn(bookingId,employeeId);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingCheckIn);
    }

    @PutMapping("{bookingId}/checkOut")
    @Operation(summary = "Realizar checkOut")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "CheckOut realizado com sucesso.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BookingOutputDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Acesso negado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))}),
            @ApiResponse(responseCode = "404", description = "Não é possível fazer checkOut de reserva cancelada.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public ResponseEntity<BookingOutputDTO> checkOut(@PathVariable Long bookingId,@RequestParam Long employeeId){
        var bookingCheckOut = bookingService.checkOut(bookingId,employeeId);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingCheckOut);
    }

    @PutMapping("{id}/cancel")
    @Operation(summary = "Cancelar reserva")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Reserva cancelada com sucesso.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BookingOutputDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Acesso negado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))}),
            @ApiResponse(responseCode = "404", description = "Não foi encontrado nenhuma reserva com esse id.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public ResponseEntity<BookingOutputDTO> cancel(@PathVariable Long id){
        var bookingCancel = bookingService.cancel(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingCancel);
    }



    @DeleteMapping("{id}")
    @Operation(summary = "Deletar uma reserva")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Reserva deletada com sucesso."),
            @ApiResponse(responseCode = "401", description = "Acesso negado.",content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class))})
    })
    public ResponseEntity<?> delete(@PathVariable Long id){
        bookingService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
