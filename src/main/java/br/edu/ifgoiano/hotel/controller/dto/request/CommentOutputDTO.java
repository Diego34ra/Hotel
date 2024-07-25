package br.edu.ifgoiano.hotel.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentOutputDTO {

    private Long id;

    private String text;

    private LocalDateTime date;

    private UserOutputDTO client;

//    @JsonBackReference // Define o lado filho da relação
//    private RoomOutputDTO room;
}
