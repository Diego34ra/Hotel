package br.edu.ifgoiano.hotel.repository;

import br.edu.ifgoiano.hotel.model.Booking;
import br.edu.ifgoiano.hotel.model.Room;
import br.edu.ifgoiano.hotel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository <Booking,Long> {
    boolean existsByClientAndRoom(User client, Room room);
}
