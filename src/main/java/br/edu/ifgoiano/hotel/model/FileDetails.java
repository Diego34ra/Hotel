package br.edu.ifgoiano.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileDetails {
    private String filename;
    private String contentType;
    private String url;
}
