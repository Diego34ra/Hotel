package br.edu.ifgoiano.hotel.service;

import br.edu.ifgoiano.hotel.model.User;

import java.util.List;

public interface UserService {
    User create(User user);
    List<User> findAll();
    User findById(Long id);
    User update(Long id, User user);
    void delete(Long id);
}
