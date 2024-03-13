package br.edu.ifgoiano.hotel.controller.dto.request;

import br.edu.ifgoiano.hotel.model.Endereco;
import br.edu.ifgoiano.hotel.model.Telefone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UsuarioDTO {
    private String nome;
    private Endereco endereco;
    private List<Telefone> telefones;
}
