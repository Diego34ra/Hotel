package br.edu.ifgoiano.hotel.controller.dto.request.userDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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
@JsonPropertyOrder({
        "id",
        "firstName",
        "lastName",
        "email"
})
public class UserSimpleOutputDTO extends RepresentationModel<UserSimpleOutputDTO> implements Serializable{
    @JsonProperty("id")
    private Long key;
    private String firstName;
    private String lastName;
    private String email;
}
