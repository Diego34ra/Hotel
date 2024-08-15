package br.edu.ifgoiano.hotel.service.impl;

import br.edu.ifgoiano.hotel.controller.exception.ResourceNotFoundException;
import br.edu.ifgoiano.hotel.model.Hospitality;
import br.edu.ifgoiano.hotel.repository.HospitalityRepository;
import br.edu.ifgoiano.hotel.service.HospitalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalityServiceImpl implements HospitalityService {

    @Autowired
    private HospitalityRepository hospitalityRepository;
    @Override
    public Hospitality create(Hospitality hospitality) {
        return hospitalityRepository.save(hospitality);
    }

    @Override
    public List<Hospitality> findAll() {
        return hospitalityRepository.findAll();
    }

    @Override
    public Hospitality findById(Long id) {
        Hospitality hospitality = hospitalityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum serviço de quarto com esse id."));
        return hospitality;
    }

    @Override
    public Hospitality update(Long id, Hospitality hospitalityUpdate) {
        Hospitality hospitality = hospitalityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum serviço de quarto com esse id."));
        if(hospitalityUpdate.getName().isEmpty())
            hospitality.setName(hospitalityUpdate.getName());
        if(hospitalityUpdate.getDescription().isEmpty())
            hospitality.setDescription(hospitalityUpdate.getDescription());
        if(hospitalityUpdate.getPrice() != null)
            hospitality.setPrice(hospitalityUpdate.getPrice());
        return hospitalityRepository.save(hospitality);
    }

    @Override
    public void delete(Long id) {
        Hospitality hospitality = hospitalityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum serviço de quarto com esse id."));
        hospitalityRepository.delete(hospitality);
    }
}
