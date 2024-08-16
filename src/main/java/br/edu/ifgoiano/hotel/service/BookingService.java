package br.edu.ifgoiano.hotel.service;

import br.edu.ifgoiano.hotel.controller.dto.request.bookingDTO.BookingInputDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.bookingDTO.BookingOutputDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.bookingDTO.BookingSimpleOutputDTO;

import java.util.List;

public interface BookingService {

    BookingOutputDTO create(BookingInputDTO booking);

    BookingOutputDTO addhospitality(Long bookingId, List<Long> hospitalityId);

    BookingOutputDTO checkIn(Long bookingId, Long empolyeeId);

    BookingOutputDTO checkOut(Long bookingId, Long empolyeeId);

    List<BookingSimpleOutputDTO> findAll();

    BookingOutputDTO findById(Long id);

    BookingOutputDTO cancel(Long id);

    void delete(Long id);
}
