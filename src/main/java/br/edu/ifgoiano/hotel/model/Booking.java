package br.edu.ifgoiano.hotel.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_booking")
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Booking {

    @Id
    @Column(name = "booking_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int days;

    private Date checkInDatePlanned;

    private Date checkOutDatePlanned;

    private BigDecimal totalValue;

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "hospitality_id")
    )
    private List<Hospitality> hospitalities;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "checkIn_id")
    private CheckIn checkIn;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "checkOut_id")
    private CheckOut checkOut;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    private Room room;

    public BigDecimal getSumTotalValue(BigDecimal price){
        return price.multiply(BigDecimal.valueOf(this.days));
    }

}
