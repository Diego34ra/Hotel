package br.edu.ifgoiano.hotel.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tb_checkIn")
public class CheckIn {

    @Id
    @Column(name = "checkIn_id")
    private Long id;
    private Date date;

    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User employee;
}
