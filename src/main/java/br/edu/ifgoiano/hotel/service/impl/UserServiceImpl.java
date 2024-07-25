package br.edu.ifgoiano.hotel.service.impl;

import br.edu.ifgoiano.hotel.controller.dto.mapper.MyModelMapper;
import br.edu.ifgoiano.hotel.controller.dto.request.UserOutputDTO;
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

    @Autowired
    private MyModelMapper mapper;
    @Override
    public UserOutputDTO create(User user) {
        if(user.getRole() == null)
            user.setRole(UserRole.getPadrao());
        user.getPhones().forEach(phone -> phone.setUser(user));
        return mapper.mapTo(userRepository.save(user), UserOutputDTO.class);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserOutputDTO findById(Long id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum cliente com esse Id."));
        return mapper.mapTo(user,UserOutputDTO.class);
    }

    @Override
    public UserOutputDTO update(Long id, User userUpdate) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum cliente com esse Id."));

        if (userUpdate.getEmail() != null && !userUpdate.getEmail().isEmpty())
            user.setEmail(userUpdate.getEmail());
        if (userUpdate.getFirstName() != null && !userUpdate.getFirstName().isEmpty())
            user.setFirstName(userUpdate.getFirstName());
        if (userUpdate.getLastName() != null && !userUpdate.getLastName().isEmpty())
            user.setLastName(userUpdate.getLastName());
        if (userUpdate.getPassword() != null && !userUpdate.getPassword().isEmpty())
            user.setPassword(userUpdate.getPassword());
        if (userUpdate.getRole() != null)
            user.setRole(userUpdate.getRole());
        if (userUpdate.getPhones() != null && !userUpdate.getPhones().isEmpty()) {
            user.setPhones(userUpdate.getPhones());
            user.getPhones().forEach(phone -> phone.setUser(user));
        }

        return mapper.mapTo(userRepository.save(user),UserOutputDTO.class);
    }

    @Override
    public void delete(Long id) {
        var usuarioDelete = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum cliente com esse Id."));
        userRepository.delete(usuarioDelete);
    }
}
