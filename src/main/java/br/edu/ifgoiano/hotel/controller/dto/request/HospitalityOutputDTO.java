package br.edu.ifgoiano.hotel.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonPropertyOrder({
        "id",
        "name",
        "description",
        "price"
})
public class HospitalityOutputDTO extends RepresentationModel<HospitalityOutputDTO> implements Serializable {
    @JsonProperty("id")
    private Long key;
    private String name;
    private String description;
    private BigDecimal price;

}
