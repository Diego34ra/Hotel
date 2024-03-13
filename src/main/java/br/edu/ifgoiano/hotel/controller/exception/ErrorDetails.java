package br.edu.ifgoiano.hotel.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {
    private Date timestamp;
    private int status;
    private String message;
    private String path;

    public ErrorDetails(String message) {
        this.message = message;
    }
}
