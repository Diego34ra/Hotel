package br.edu.ifgoiano.hotel.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({
        "id",
        "name",
        "description"
})
public class ConvenienceOutputDTO extends RepresentationModel<ConvenienceOutputDTO> implements Serializable {

    @JsonProperty("id")
    private Long key;

    private String name;

    private String description;
}