package br.edu.ifgoiano.hotel.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity(name = "tb_hospitality")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Hospitality {

    @Id
    @Column(name = "hospitality_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;

}
