package br.edu.ifgoiano.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {

    CLIENT,
    EMPLOYEE,
    ADMINISTRATOR;

    public static UserRole getPadrao(){
        return CLIENT;
    }
}
