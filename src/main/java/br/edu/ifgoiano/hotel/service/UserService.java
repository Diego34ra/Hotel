package br.edu.ifgoiano.hotel.service;

import br.edu.ifgoiano.hotel.controller.dto.request.userDTOs.UserInputDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.userDTOs.UserDetailOutputDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.userDTOs.UserSimpleOutputDTO;
import br.edu.ifgoiano.hotel.model.User;

import java.util.List;

public interface UserService {
    UserDetailOutputDTO create(UserInputDTO user);
    List<UserSimpleOutputDTO> findAll();
    UserDetailOutputDTO findById(Long id);
    UserDetailOutputDTO update(Long id, User user);
    void delete(Long id);
}
