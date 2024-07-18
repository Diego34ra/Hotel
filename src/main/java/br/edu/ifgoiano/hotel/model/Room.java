package br.edu.ifgoiano.hotel.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity(name = "tb_room")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;
    private String description;
    private BigDecimal price;
    private Boolean available;
    private Integer capacity;
    private Integer floor;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy = "room")
    private List<Comment> comments;

    @Enumerated(EnumType.STRING)
    private RoomType type;

}
