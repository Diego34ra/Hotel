package br.edu.ifgoiano.hotel.controller.dto.request.bookingDTO;

import br.edu.ifgoiano.hotel.controller.dto.request.userDTO.UserSimpleOutputDTO;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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
public class CheckOutOutputDTO {

    private Long id;
    private Date date;
    private UserSimpleOutputDTO employee;
}
