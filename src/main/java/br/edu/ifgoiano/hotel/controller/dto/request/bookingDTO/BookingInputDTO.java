package br.edu.ifgoiano.hotel.controller.dto.request.bookingDTO;

import br.edu.ifgoiano.hotel.model.*;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({
        "days",
        "checkInDatePlanned",
        "checkOutDatePlanned",
        "hospitalities",
        "client",
        "room"
})
public class BookingInputDTO {

    private int days;

    private Date checkInDatePlanned;

    private Date checkOutDatePlanned;

    private List<Hospitality> hospitalities;

    private User client;

    private Room room;

}
