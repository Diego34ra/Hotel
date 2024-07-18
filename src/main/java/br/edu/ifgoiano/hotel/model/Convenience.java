package br.edu.ifgoiano.hotel.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_convenience")
@Getter
@Setter
@AllArgsConstructor
public class Convenience {

    @Id
    @Column(name = "convenience_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;
}
