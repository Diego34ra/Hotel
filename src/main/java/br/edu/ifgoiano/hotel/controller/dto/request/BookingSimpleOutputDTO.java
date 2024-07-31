package br.edu.ifgoiano.hotel.controller.dto.request;

import br.edu.ifgoiano.hotel.controller.dto.request.userDTOs.UserDetailOutputDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.userDTOs.UserSimpleOutputDTO;
import br.edu.ifgoiano.hotel.model.BookingStatus;
import br.edu.ifgoiano.hotel.model.CheckIn;
import br.edu.ifgoiano.hotel.model.CheckOut;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "days",
        "totalValue",
        "bookingStatus",
        "checkInDatePlanned",
        "checkOutDatePlanned",
        "client",
        "room"
})
public class BookingSimpleOutputDTO {
    private Long id;
    private int days;
    private Date checkInDatePlanned;
    private Date checkOutDatePlanned;
    private BigDecimal totalValue;
    private BookingStatus bookingStatus;
    private UserSimpleOutputDTO client;
    private RoomSimpleOutputDTO room;
}
