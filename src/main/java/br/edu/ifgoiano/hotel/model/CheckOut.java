package br.edu.ifgoiano.hotel.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "tb_checkOut")
@Getter
@Setter
public class CheckOut {

    @Id
    @Column(name = "checkOut_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User employee;
}
