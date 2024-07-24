package br.edu.ifgoiano.hotel.controller.dto.request.userDTOs;

import br.edu.ifgoiano.hotel.model.Phone;
import br.edu.ifgoiano.hotel.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class UserDetailOutputDTO {
    private Long id;

    private String firstName;

    private String lastName;

    private String cpf;

    private String email;

    private UserRole role;

    private List<Phone> phones;
}
