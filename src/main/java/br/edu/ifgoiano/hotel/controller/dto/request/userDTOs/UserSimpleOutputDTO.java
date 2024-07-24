package br.edu.ifgoiano.hotel.controller.dto.request.userDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserSimpleOutputDTO {
    private Long id;
    private String nome;
    private String email;
}
