package br.edu.ifgoiano.hotel.service.impl;

import br.edu.ifgoiano.hotel.controller.exception.ResourceNotFoundException;
import br.edu.ifgoiano.hotel.model.User;
import br.edu.ifgoiano.hotel.model.UserRole;
import br.edu.ifgoiano.hotel.repository.UserRepository;
import br.edu.ifgoiano.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User create(User user) {
        if(user.getRole() == null)
            user.setRole(UserRole.getPadrao());
        user.getPhones().forEach(phone -> phone.setUser(user));
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum cliente com esse Id."));
    }

    @Override
    public User update(Long id, User user) {
        return null;
    }

    @Override
    public void delete(Long id) {
        var usuarioDelete = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum cliente com esse Id."));
        userRepository.delete(usuarioDelete);
    }
}
