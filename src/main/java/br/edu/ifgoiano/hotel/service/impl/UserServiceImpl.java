package br.edu.ifgoiano.hotel.service.impl;

import br.edu.ifgoiano.hotel.controller.dto.mapper.MyModelMapper;
import br.edu.ifgoiano.hotel.controller.dto.request.userDTOs.UserInputDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.userDTOs.UserDetailOutputDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.userDTOs.UserSimpleOutputDTO;
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
    public UserDetailOutputDTO create(UserInputDTO user) {
        var userCreate = mapper.mapTo(user,User.class);
        if(userCreate.getRole() == null)
            userCreate.setRole(UserRole.getPadrao());
        userCreate.getPhones().forEach(phone -> phone.setUser(userCreate));
        return mapper.mapTo(userRepository.save(userCreate), UserDetailOutputDTO.class);
    }

    @Override
    public List<UserSimpleOutputDTO> findAll() {
        return mapper.toList(userRepository.findAll(), UserSimpleOutputDTO.class);
    }

    @Override
    public UserDetailOutputDTO findById(Long id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum cliente com esse Id."));
        return mapper.mapTo(user,UserDetailOutputDTO.class);
    }

    @Override
    public UserDetailOutputDTO update(Long id, User userUpdate) {
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

        return mapper.mapTo(userRepository.save(user),UserDetailOutputDTO.class);
    }

    @Override
    public void delete(Long id) {
        //Métodos idempotentes!
        //var usuarioDelete = userRepository.findById(id)
        //        .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum cliente com esse Id."));
        userRepository.deleteById(id);
    }
}
