package br.edu.ifgoiano.hotel.service.impl;

import br.edu.ifgoiano.hotel.controller.ConvenienceController;
import br.edu.ifgoiano.hotel.controller.dto.mapper.MyModelMapper;
import br.edu.ifgoiano.hotel.controller.dto.request.ConvenienceOutputDTO;
import br.edu.ifgoiano.hotel.controller.exception.ResourceNotFoundException;
import br.edu.ifgoiano.hotel.model.Convenience;
import br.edu.ifgoiano.hotel.repository.ConvenienceRepository;
import br.edu.ifgoiano.hotel.service.ConvenienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ConvenienceServiceImpl implements ConvenienceService {

    @Autowired
    private ConvenienceRepository convenienceRepository;

    @Autowired
    private MyModelMapper mapper;

    @Override
    public ConvenienceOutputDTO create(Convenience convenience) {
        return mapper.mapTo(convenienceRepository.save(convenience), ConvenienceOutputDTO.class)
                .add(linkTo(methodOn(ConvenienceController.class).findAll()).withRel("list-convenience"));
    }

    @Override
    public List<ConvenienceOutputDTO> findAll() {
        return mapper.toList(convenienceRepository.findAll(), ConvenienceOutputDTO.class);
    }

    @Override
    public ConvenienceOutputDTO update(Long id, Convenience convenienceUptade) {
        Convenience convenience = convenienceRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Não foi encontrada nenhuma comodidade com o id informado."));
        if(!convenienceUptade.getName().isEmpty())
            convenience.setName(convenienceUptade.getName());
        if(!convenienceUptade.getDescription().isEmpty())
            convenience.setDescription(convenienceUptade.getDescription());
        return mapper.mapTo(convenienceRepository.save(convenience), ConvenienceOutputDTO.class)
                .add(linkTo(methodOn(ConvenienceController.class).findAll()).withRel("list-convenience"));
    }

    @Override
    public void delete(Long id) {
        convenienceRepository.deleteById(id);
    }
}
