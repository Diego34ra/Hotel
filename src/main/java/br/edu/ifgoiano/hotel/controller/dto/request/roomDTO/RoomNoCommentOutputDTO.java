package br.edu.ifgoiano.hotel.controller.dto.request.roomDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@JsonPropertyOrder({
        "id",
        "description",
        "price",
        "available",
        "capacity",
        "floor",
        "type"
})
public class RoomNoCommentOutputDTO extends RepresentationModel<RoomNoCommentOutputDTO> implements Serializable {
    @JsonProperty("id")
    private Long key;
    private String description;
    private BigDecimal price;
    private Boolean available;
    private Integer capacity;
    private Integer floor;
    private String type;
}
