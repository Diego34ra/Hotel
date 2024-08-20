package br.edu.ifgoiano.hotel.service;

import br.edu.ifgoiano.hotel.controller.dto.request.userDTO.UserInputDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.userDTO.UserDetailOutputDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.userDTO.UserSimpleOutputDTO;
import br.edu.ifgoiano.hotel.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    UserDetailOutputDTO create(UserInputDTO user);
    List<UserSimpleOutputDTO> findAll();

    User findById(Long id);

    UserDetailOutputDTO findOneById(Long id);

    UserDetails findByEmail(String email);

    UserDetailOutputDTO update(Long id, User user);
    void delete(Long id);

    boolean emailExists(String email);

    boolean cpfExists(String cpf);
}
