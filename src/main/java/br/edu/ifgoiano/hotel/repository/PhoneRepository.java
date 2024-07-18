package br.edu.ifgoiano.hotel.repository;

import br.edu.ifgoiano.hotel.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<Phone,Long> {
}
