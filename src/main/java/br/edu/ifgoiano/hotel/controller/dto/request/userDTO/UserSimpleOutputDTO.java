package br.edu.ifgoiano.hotel.controller.dto.request.userDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class UserSimpleOutputDTO{
    private Long key;
    private String firstName;
    private String lastName;
    private String email;
}
