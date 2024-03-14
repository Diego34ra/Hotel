package br.edu.ifgoiano.hotel.service;

import br.edu.ifgoiano.hotel.model.Quarto;

import java.util.List;

public interface QuartoService {
    Quarto create(Quarto quarto);
    List<Quarto> findAll();
    Quarto findById(Long id);
    void delete(Long id);
}
