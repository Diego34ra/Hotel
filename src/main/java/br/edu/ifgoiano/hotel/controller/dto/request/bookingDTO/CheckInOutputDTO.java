package br.edu.ifgoiano.hotel.controller.dto.request.bookingDTO;

import br.edu.ifgoiano.hotel.controller.dto.request.userDTO.UserSimpleOutputDTO;
import br.edu.ifgoiano.hotel.model.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({
        "id",
        "date",
        "employee"
})
public class CheckInOutputDTO {

    private Long id;
    private Date date;
    private UserSimpleOutputDTO employee;
}
