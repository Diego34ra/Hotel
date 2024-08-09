package br.edu.ifgoiano.hotel.controller.dto.request.userDTO;

import br.edu.ifgoiano.hotel.model.Phone;
import br.edu.ifgoiano.hotel.model.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.List;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@JsonPropertyOrder({
        "id",
        "firstName",
        "lastName",
        "cpf",
        "email",
        "role",
        "phones"
})
public class UserDetailOutputDTO  extends RepresentationModel<UserDetailOutputDTO> implements Serializable {
    @JsonProperty("id")
    private Long key;

    private String firstName;

    private String lastName;

    private String cpf;

    private String email;

    private UserRole role;

    private List<Phone> phones;
}
