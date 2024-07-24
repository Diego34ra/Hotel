package br.edu.ifgoiano.hotel.controller.dto.request.userDTOs;

import br.edu.ifgoiano.hotel.model.Phone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class UserOutputDTO {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private List<Phone> phones;
}
