package br.edu.ifgoiano.hotel.controller.dto.request;

import br.edu.ifgoiano.hotel.controller.dto.request.userDTOs.UserOutputDTO;
import br.edu.ifgoiano.hotel.model.*;
import com.fasterxml.jackson.annotation.JsonInclude;
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
public class BookingOutputDTO {
    private Long id;
    private int days;
    private Date checkInDatePlanned;
    private Date checkOutDatePlanned;
    private BigDecimal totalValue;
    private CheckIn checkIn;
    private CheckOut checkOut;
    private BookingStatus bookingStatus;
    private UserOutputDTO client;
    private RoomOutputDTO room;
}
