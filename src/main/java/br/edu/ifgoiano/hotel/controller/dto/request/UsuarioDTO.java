package br.edu.ifgoiano.hotel.controller.dto.request;

import br.edu.ifgoiano.hotel.model.Phone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UsuarioDTO {
    private String nome;
    private List<Phone> phones;
}
