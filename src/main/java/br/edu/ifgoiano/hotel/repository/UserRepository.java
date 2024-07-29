package br.edu.ifgoiano.hotel.repository;

import br.edu.ifgoiano.hotel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    UserDetails findByEmail(String login);

    boolean existsByEmail(String email);

    boolean existsByCpf(String cpf);
}
