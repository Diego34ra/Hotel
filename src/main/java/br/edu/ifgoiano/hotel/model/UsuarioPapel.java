package br.edu.ifgoiano.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum UsuarioPapel {

    CLIENTE,
    ADMINISTRADOR;

    public static UsuarioPapel getPadrao(){
        return CLIENTE;
    }
}
