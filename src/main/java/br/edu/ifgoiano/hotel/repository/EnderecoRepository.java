package br.edu.ifgoiano.hotel.repository;

import br.edu.ifgoiano.hotel.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco,Long> {
}
