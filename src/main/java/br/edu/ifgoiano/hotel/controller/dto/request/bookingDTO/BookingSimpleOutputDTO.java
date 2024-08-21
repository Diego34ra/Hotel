package br.edu.ifgoiano.hotel.controller.dto.request.bookingDTO;

import br.edu.ifgoiano.hotel.controller.dto.request.roomDTO.RoomSimpleOutputDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.userDTO.UserSimpleOutputDTO;
import br.edu.ifgoiano.hotel.model.BookingStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
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
public class BookingSimpleOutputDTO extends RepresentationModel<BookingSimpleOutputDTO> implements Serializable {
    @JsonProperty("id")
    private Long key;
    private int days;
    private Date checkInDatePlanned;
    private Date checkOutDatePlanned;
    private Date checkInDate;
    private Date checkOutDate;
    private BigDecimal totalValue;
    private BookingStatus bookingStatus;
    private UserSimpleOutputDTO client;
    private RoomSimpleOutputDTO room;
}
