package br.edu.ifgoiano.hotel.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class RoomNoCommentOutputDTO {
    private Long id;
    private String description;
    private BigDecimal price;
    private Boolean available;
    private Integer capacity;
    private Integer floor;
    private String type;
}