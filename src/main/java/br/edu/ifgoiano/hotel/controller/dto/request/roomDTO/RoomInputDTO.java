package br.edu.ifgoiano.hotel.controller.dto.request.roomDTO;

import br.edu.ifgoiano.hotel.model.RoomType;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({
        "description",
        "price",
        "available",
        "capacity",
        "floor",
        "type"

})
public class RoomInputDTO {
    private String description;
    private BigDecimal price;
    private Boolean available;
    private Integer capacity;
    private Integer floor;
    private RoomType type;
}
