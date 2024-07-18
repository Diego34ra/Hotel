package br.edu.ifgoiano.hotel.controller.dto.request;

import br.edu.ifgoiano.hotel.model.Phone;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class UserDTO {
    private String nome;
    private List<Phone> phones;
}
