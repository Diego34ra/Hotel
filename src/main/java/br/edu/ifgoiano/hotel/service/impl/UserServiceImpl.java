package br.edu.ifgoiano.hotel.service.impl;

import br.edu.ifgoiano.hotel.controller.UserController;
import br.edu.ifgoiano.hotel.controller.dto.mapper.MyModelMapper;
import br.edu.ifgoiano.hotel.controller.dto.request.userDTO.UserInputDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.userDTO.UserDetailOutputDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.userDTO.UserSimpleOutputDTO;
import br.edu.ifgoiano.hotel.controller.exception.ResourceBadRequestException;
import br.edu.ifgoiano.hotel.controller.exception.ResourceNotFoundException;
import br.edu.ifgoiano.hotel.model.User;
import br.edu.ifgoiano.hotel.model.UserRole;
import br.edu.ifgoiano.hotel.repository.UserRepository;
import br.edu.ifgoiano.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MyModelMapper mapper;
    @Override
    public UserDetailOutputDTO create(UserInputDTO user) {
        var userCreate = mapper.mapTo(user,User.class);

        if(userCreate.getRole() == null)
            userCreate.setRole(UserRole.getPadrao());

        if (emailExists(user.getEmail()))
            throw new ResourceBadRequestException("Esse email já está cadastrado");


        if (cpfExists(user.getCpf()))
            throw new ResourceBadRequestException("Esse cpf já está cadastrado");


        String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        userCreate.setPassword(encryptedPassword);
        userCreate.getPhones().forEach(phone -> phone.setUser(userCreate));

        UserDetailOutputDTO userDTO = mapper.mapTo(userRepository.save(userCreate), UserDetailOutputDTO.class);
        return userDTO.add(linkTo(methodOn(UserController.class).findAll()).withRel("list-users"))
                .add(linkTo(methodOn(UserController.class).findById(userDTO.getKey())).withSelfRel());
    }

    @Override
    public List<UserSimpleOutputDTO> findAll() {
        List<UserSimpleOutputDTO> userSimpleOutputDTOList = mapper.toList(
                userRepository.findAll(), UserSimpleOutputDTO.class).stream()
                .map(userDTO -> userDTO.add(linkTo(methodOn(UserController.class)
                        .findById(userDTO.getKey()))
                        .withSelfRel())).toList();

        return userSimpleOutputDTOList;
    }

    @Override
    public UserDetailOutputDTO findById(Long id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum cliente com esse Id."));
        return mapper.mapTo(user,UserDetailOutputDTO.class)
                .add(linkTo(methodOn(UserController.class).findAll()).withRel("list-users"));
    }

    @Override
    public UserDetails findByEmail(String email) {
        return userRepository.findByEmail(email);
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

        UserDetailOutputDTO userDTO = mapper.mapTo(userRepository.save(user), UserDetailOutputDTO.class);
        return userDTO.add(linkTo(methodOn(UserController.class).findAll()).withRel("list-users"))
                .add(linkTo(methodOn(UserController.class).findById(userDTO.getKey())).withSelfRel());
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean cpfExists(String cpf) {
        return userRepository.existsByCpf(cpf);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username);
    }
}
