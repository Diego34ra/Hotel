package br.edu.ifgoiano.hotel.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity(name = "tb_quarto")
public class Quarto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quarto_id")
    private Long id;
    private String descricao;
    private BigDecimal preco;
    private Boolean disponivel;
    private int capacidade;
    private int andar;

    @Enumerated(EnumType.STRING)
    private QuartoTipo tipo;

}
