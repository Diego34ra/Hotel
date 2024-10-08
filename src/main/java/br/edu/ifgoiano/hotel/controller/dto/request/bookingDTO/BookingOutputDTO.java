package br.edu.ifgoiano.hotel.controller.dto.request.bookingDTO;

import br.edu.ifgoiano.hotel.controller.dto.request.HospitalityOutputDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.roomDTO.RoomNoCommentOutputDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.roomDTO.RoomOutputDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.userDTO.UserDetailOutputDTO;
import br.edu.ifgoiano.hotel.model.*;
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
import java.util.List;

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
        "hospitalities",
        "checkIn",
        "checkOut"
})
public class BookingOutputDTO extends RepresentationModel<BookingOutputDTO> implements Serializable {
    @JsonProperty("id")
    private Long key;
    private int days;
    private Date checkInDatePlanned;
    private Date checkOutDatePlanned;
    private BigDecimal totalValue;
    private CheckInOutputDTO checkIn;
    private CheckOutOutputDTO checkOut;
    private BookingStatus bookingStatus;
    private UserDetailOutputDTO client;
    private RoomNoCommentOutputDTO room;
    private List<HospitalityOutputDTO> hospitalities;
}
