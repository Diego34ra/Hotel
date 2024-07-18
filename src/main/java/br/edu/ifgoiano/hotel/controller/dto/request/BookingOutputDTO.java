package br.edu.ifgoiano.hotel.controller.dto.request;

import br.edu.ifgoiano.hotel.model.*;
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
public class BookingOutputDTO {
    private Long id;
    private int days;
    private Date checkInDate;
    private Date checkOutDate;
    private BigDecimal totalValue;
    private CheckIn checkIn;
    private CheckOut checkOut;
    private BookingStatus bookingStatus;
    private UserOutputDTO client;
    private RoomOutputDTO room;
}
