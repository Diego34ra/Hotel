package br.edu.ifgoiano.hotel.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "tb_booking")
public class Booking {

    @Id
    @Column(name = "booking_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int days;

    private Date chekIn;

    private Date chekOut;

    private BigDecimal totalValue;

    @OneToOne(fetch = FetchType.EAGER)
    private Comment comment;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    private Room room;

}
