package br.edu.ifgoiano.hotel.service;

import br.edu.ifgoiano.hotel.controller.dto.request.UserOutputDTO;
import br.edu.ifgoiano.hotel.model.User;

import java.util.List;

public interface UserService {
    UserOutputDTO create(User user);
    List<User> findAll();
    UserOutputDTO findById(Long id);
    UserOutputDTO update(Long id, User user);
    void delete(Long id);
}
