package br.edu.ifgoiano.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum UsuarioPapel {

    PACIENTE,
    PROFISSIONAL,
    ADMINISTRADOR;

    private String papel;
}
