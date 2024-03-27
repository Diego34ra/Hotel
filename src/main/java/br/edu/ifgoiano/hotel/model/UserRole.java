package br.edu.ifgoiano.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public enum UserRole {

    CLIENT,
    ADMINISTRATOR;

    public static UserRole getPadrao(){
        return CLIENT;
    }
}
