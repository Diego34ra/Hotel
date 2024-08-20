package br.edu.ifgoiano.hotel.repository;

import br.edu.ifgoiano.hotel.model.Room;
import br.edu.ifgoiano.hotel.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {

    @Query("SELECT r FROM tb_room r " +
            "WHERE (:type IS NULL OR r.type = :type) " +
            "AND (:capacity IS NULL OR r.capacity = :capacity) " +
            "AND (:available IS NULL OR r.available = :available) " +
            "ORDER BY r.price ASC")
    List<Room> findByFilters(@Param("type") RoomType type,
                             @Param("capacity") Integer capacity,
                             @Param("available") Boolean available);

    @Query("SELECT r FROM tb_room r " +
            "WHERE (:type IS NULL OR r.type = :type) " +
            "AND (:capacity IS NULL OR r.capacity = :capacity) " +
            "AND (:available IS NULL OR r.available = :available) " +
            "ORDER BY r.price DESC")
    List<Room> findByFiltersDesc(@Param("type") RoomType type,
                                 @Param("capacity") Integer capacity,
                                 @Param("available") Boolean available);
}
