package br.edu.ifgoiano.hotel.service.impl;

import br.edu.ifgoiano.hotel.controller.exception.ResourceNotFoundException;
import br.edu.ifgoiano.hotel.model.Convenience;
import br.edu.ifgoiano.hotel.repository.ConvenienceRepository;
import br.edu.ifgoiano.hotel.service.ConvenienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConvenienceServiceImpl implements ConvenienceService {

    @Autowired
    private ConvenienceRepository convenienceRepository;

    @Override
    public Convenience create(Convenience convenience) {
        return convenienceRepository.save(convenience);
    }

    @Override
    public List<Convenience> findAll() {
        return convenienceRepository.findAll();
    }

    @Override
    public Convenience update(Long id, Convenience convenienceUptade) {
        Convenience convenience = convenienceRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Não foi encontrada nenhuma comodidade com o id informado."));
        if(!convenienceUptade.getName().isEmpty())
            convenience.setName(convenienceUptade.getName());
        if(!convenienceUptade.getDescription().isEmpty())
            convenience.setDescription(convenienceUptade.getDescription());
        return convenienceRepository.save(convenience);
    }

    @Override
    public void delete(Long id) {
        Convenience convenience = convenienceRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Não foi encontrada nenhuma comodidade com o id informado."));
        convenienceRepository.delete(convenience);
    }
}
