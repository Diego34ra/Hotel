package br.edu.ifgoiano.hotel.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity(name = "tb_room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;
    private String description;
    private BigDecimal price;
    private Boolean available;
    private int capacity;
    private int floor;

    @Enumerated(EnumType.STRING)
    private RoomType type;

}
