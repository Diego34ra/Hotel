package br.edu.ifgoiano.hotel.service;

import br.edu.ifgoiano.hotel.controller.dto.request.ConvenienceOutputDTO;
import br.edu.ifgoiano.hotel.model.Convenience;

import java.util.List;

public interface ConvenienceService {

    ConvenienceOutputDTO create(Convenience convenience);

    List<ConvenienceOutputDTO> findAll();

    ConvenienceOutputDTO update(Long id, Convenience convenience);

    void delete(Long id);
}
