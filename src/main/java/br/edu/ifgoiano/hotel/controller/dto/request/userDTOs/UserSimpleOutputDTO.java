package br.edu.ifgoiano.hotel.controller.dto.request.userDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class UserSimpleOutputDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
