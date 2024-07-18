package br.edu.ifgoiano.hotel.repository;

import br.edu.ifgoiano.hotel.model.Convenience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConvenienceRepository extends JpaRepository<Convenience,Long> {
}
