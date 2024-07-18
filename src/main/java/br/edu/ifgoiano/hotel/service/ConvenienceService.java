package br.edu.ifgoiano.hotel.service;

import br.edu.ifgoiano.hotel.model.Convenience;

import java.util.List;

public interface ConvenienceService {

    Convenience create(Convenience convenience);

    List<Convenience> findAll();

    Convenience update(Long id, Convenience convenience);

    void delete(Long id);
}
