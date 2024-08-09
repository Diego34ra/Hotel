package br.edu.ifgoiano.hotel.service.impl;

import br.edu.ifgoiano.hotel.controller.HospitalityController;
import br.edu.ifgoiano.hotel.controller.dto.request.HospitalityOutputDTO;
import br.edu.ifgoiano.hotel.controller.dto.mapper.MyModelMapper;
import br.edu.ifgoiano.hotel.controller.exception.ResourceNotFoundException;
import br.edu.ifgoiano.hotel.model.Hospitality;
import br.edu.ifgoiano.hotel.repository.HospitalityRepository;
import br.edu.ifgoiano.hotel.service.HospitalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class HospitalityServiceImpl implements HospitalityService {

    @Autowired
    private HospitalityRepository hospitalityRepository;

    @Autowired
    private MyModelMapper mapper;

    @Override
    public HospitalityOutputDTO create(Hospitality hospitality) {
        return mapper.mapTo(hospitalityRepository.save(hospitality), HospitalityOutputDTO.class)
                .add(linkTo(methodOn(HospitalityController.class).findAll()).withRel("list-hospitality"));
    }

    @Override
    public List<HospitalityOutputDTO> findAll() {
        return mapper.toList(hospitalityRepository.findAll(), HospitalityOutputDTO.class);
    }

    @Override
    public Hospitality findById(Long id) {
        Hospitality hospitality = hospitalityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum serviço de quarto com esse id."));
        return hospitality;
    }

    @Override
    public HospitalityOutputDTO update(Long id, Hospitality hospitalityUpdate) {
        Hospitality hospitality = hospitalityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum serviço de quarto com esse id."));
        if(hospitalityUpdate.getName().isEmpty())
            hospitality.setName(hospitalityUpdate.getName());
        if(hospitalityUpdate.getDescription().isEmpty())
            hospitality.setDescription(hospitalityUpdate.getDescription());
        if(hospitalityUpdate.getPrice() != null)
            hospitality.setPrice(hospitalityUpdate.getPrice());
        return mapper.mapTo(hospitalityRepository.save(hospitality), HospitalityOutputDTO.class)
                .add(linkTo(methodOn(HospitalityController.class).findAll()).withRel("list-hospitality"));
    }

    @Override
    public void delete(Long id) {
        Hospitality hospitality = hospitalityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum serviço de quarto com esse id."));
        hospitalityRepository.delete(hospitality);
    }
}
