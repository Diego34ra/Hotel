package br.edu.ifgoiano.hotel.repository;

import br.edu.ifgoiano.hotel.model.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckInRepository extends JpaRepository<CheckIn,Long> {
}
