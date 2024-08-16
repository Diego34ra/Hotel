package br.edu.ifgoiano.hotel.service;

import br.edu.ifgoiano.hotel.controller.dto.request.HospitalityOutputDTO;
import br.edu.ifgoiano.hotel.model.Hospitality;

import java.util.List;

public interface HospitalityService {

    HospitalityOutputDTO create(Hospitality hospitality);

    List<HospitalityOutputDTO> findAll();

    Hospitality findById(Long id);

    HospitalityOutputDTO update(Long id, Hospitality hospitality);

    void delete(Long id);
}
