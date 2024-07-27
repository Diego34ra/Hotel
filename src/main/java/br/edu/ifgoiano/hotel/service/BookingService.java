package br.edu.ifgoiano.hotel.service;

import br.edu.ifgoiano.hotel.controller.dto.request.BookingInputDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.BookingOutputDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.BookingSimpleOutputDTO;
import br.edu.ifgoiano.hotel.model.Booking;

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
