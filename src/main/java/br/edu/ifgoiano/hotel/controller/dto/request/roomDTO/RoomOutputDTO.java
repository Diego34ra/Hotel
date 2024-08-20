package br.edu.ifgoiano.hotel.controller.dto.request.roomDTO;

import br.edu.ifgoiano.hotel.controller.dto.request.CommentOutputDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "description",
        "price",
        "available",
        "capacity",
        "floor",
        "type",
        "comments"
})
public class RoomOutputDTO extends RepresentationModel<RoomOutputDTO> implements Serializable {
    @JsonProperty("id")
    private Long key;
    private String description;
    private BigDecimal price;
    private Boolean available;
    private Integer capacity;
    private Integer floor;
    private String type;
    @JsonManagedReference // Define o lado pai da relação
    private List<CommentOutputDTO> comments;
}
