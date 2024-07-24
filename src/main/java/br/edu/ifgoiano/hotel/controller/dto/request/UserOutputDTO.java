package br.edu.ifgoiano.hotel.controller.dto.request;

import br.edu.ifgoiano.hotel.model.Phone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class UserOutputDTO {
    private Long id;

    private String firstName;

    private String lastName;

    private String cpf;

    private Date birth;

    private String email;

    private List<Phone> phones;
}
