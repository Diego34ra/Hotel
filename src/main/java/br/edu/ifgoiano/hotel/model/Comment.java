package br.edu.ifgoiano.hotel.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_comment")
public class Comment {

    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String commentText;

//    @OneToOne(fetch = FetchType.EAGER)
//    private Booking booking;

}
