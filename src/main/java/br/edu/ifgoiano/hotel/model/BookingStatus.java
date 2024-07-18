package br.edu.ifgoiano.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BookingStatus {

    CONFIRMED,
    FINISHED,
    CANCELED;

    public static BookingStatus getPadrao(){
        return CONFIRMED;
    }
}
