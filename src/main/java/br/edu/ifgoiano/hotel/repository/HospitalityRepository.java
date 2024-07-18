package br.edu.ifgoiano.hotel.repository;

import br.edu.ifgoiano.hotel.model.Hospitality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalityRepository extends JpaRepository<Hospitality,Long> {
}
