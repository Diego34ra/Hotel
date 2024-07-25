package br.edu.ifgoiano.hotel.controller;

import br.edu.ifgoiano.hotel.controller.dto.request.BookingOutputDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.BookingSimpleOutputDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.HospitalityDTO;
import br.edu.ifgoiano.hotel.model.Booking;
import br.edu.ifgoiano.hotel.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/hotel/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingOutputDTO> create(@RequestBody Booking booking){
        var bookingCreated = bookingService.create(booking);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingCreated);
    }

    @PostMapping("{bookingId}/checkIn")
    public ResponseEntity<Booking> checkIn(@PathVariable Long bookingId,@RequestParam Long employeeId){
        var bookingCheckIn = bookingService.checkIn(bookingId,employeeId);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingCheckIn);
    }

    @PostMapping("{bookingId}/hospitality")
    public ResponseEntity<Booking> addHospitality(@PathVariable Long bookingId,@RequestBody HospitalityDTO hospitalityDTO){
        var bookingAddHospitality = bookingService.addhospitality(bookingId,hospitalityDTO.getHospitalityIds());
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingAddHospitality);
    }

    @PostMapping("{bookingId}/checkOut")
    public ResponseEntity<Booking> checkOut(@PathVariable Long bookingId,@RequestParam Long employeeId){
        var bookingCheckOut = bookingService.checkOut(bookingId,employeeId);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingCheckOut);
    }

    @PutMapping("{id}/cancel")
    public ResponseEntity<Booking> cancel(@PathVariable Long id){
        var bookingCancel = bookingService.cancel(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingCancel);
    }

    @GetMapping
    public ResponseEntity<List<BookingSimpleOutputDTO>> findAll(){
        var bookings = bookingService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(bookings);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id){
        bookingService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
