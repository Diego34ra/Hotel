package br.edu.ifgoiano.hotel.service;

import br.edu.ifgoiano.hotel.model.Hospitality;

import java.util.List;

public interface HospitalityService {

    Hospitality create(Hospitality hospitality);

    List<Hospitality> findAll();

    Hospitality findById(Long id);

    Hospitality update(Long id, Hospitality hospitality);

    void delete(Long id);
}
