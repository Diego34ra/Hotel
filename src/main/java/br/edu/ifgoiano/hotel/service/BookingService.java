package br.edu.ifgoiano.hotel.service;

import br.edu.ifgoiano.hotel.model.Booking;

import java.util.List;

public interface BookingService {

    Booking create(Booking booking);

    Booking addhospitality(Long bookingId, List<Long> hospitalityId);

    Booking checkIn(Long bookingId, Long empolyeeId);

    Booking checkOut(Long bookingId, Long empolyeeId);

    List<Booking> findAll();

    Booking cancel(Long id);

    void delete(Long id);
}
