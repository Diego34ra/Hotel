package br.edu.ifgoiano.hotel.controller.dto.request;

import br.edu.ifgoiano.hotel.controller.dto.request.userDTO.UserSimpleOutputDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({
        "id",
        "text",
        "date",
        "client"
})
public class CommentOutputDTO extends RepresentationModel<CommentOutputDTO> implements Serializable {

    @JsonProperty("id")
    private Long key;

    private String text;

    private LocalDateTime date;

    private UserSimpleOutputDTO client;
}