package br.edu.ifgoiano.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BookingStatus {
    PENDING,
    CONFIRMED,
    CANCELED
}
