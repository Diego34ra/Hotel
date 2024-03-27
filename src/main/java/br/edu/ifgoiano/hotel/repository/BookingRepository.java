package br.edu.ifgoiano.hotel.repository;

import br.edu.ifgoiano.hotel.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository <Booking,Long> {
}
