package br.edu.ifgoiano.hotel.service;

import br.edu.ifgoiano.hotel.controller.dto.request.BookingOutputDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.BookingSimpleOutputDTO;
import br.edu.ifgoiano.hotel.model.Booking;

import java.util.List;

public interface BookingService {

    BookingOutputDTO create(Booking booking);

    Booking addhospitality(Long bookingId, List<Long> hospitalityId);

    Booking checkIn(Long bookingId, Long empolyeeId);

    Booking checkOut(Long bookingId, Long empolyeeId);

    List<BookingSimpleOutputDTO> findAll();

    Booking cancel(Long id);

    void delete(Long id);
}
