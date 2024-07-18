package br.edu.ifgoiano.hotel.repository;

import br.edu.ifgoiano.hotel.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {
}
