package br.edu.ifgoiano.hotel.controller.dto.request;

import br.edu.ifgoiano.hotel.controller.dto.request.userDTOs.UserDetailOutputDTO;
import br.edu.ifgoiano.hotel.model.*;
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
        "room",
        "checkIn",
        "checkOut"
})
public class BookingOutputDTO {
    private Long id;
    private int days;
    private Date checkInDatePlanned;
    private Date checkOutDatePlanned;
    private BigDecimal totalValue;
    private CheckIn checkIn;
    private CheckOut checkOut;
    private BookingStatus bookingStatus;
    private UserDetailOutputDTO client;
    private RoomOutputDTO room;
}
