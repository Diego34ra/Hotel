package br.edu.ifgoiano.hotel.controller.dto.request.userDTOs;

import br.edu.ifgoiano.hotel.model.Phone;
import br.edu.ifgoiano.hotel.model.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInputDTO {
    private String firstName;

    private String lastName;
    @Pattern(regexp = "\\d{11}", message = "O CPF deve ter 11 dígitos")
    private String cpf;

    private Date birth;
    @Email(message = "O email é inválido")
    private String email;

    private String password;

    private UserRole role;

    private List<Phone> phones;
}
