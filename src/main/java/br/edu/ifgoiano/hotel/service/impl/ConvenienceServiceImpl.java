package br.edu.ifgoiano.hotel.service.impl;

import br.edu.ifgoiano.hotel.model.Convenience;
import br.edu.ifgoiano.hotel.repository.ConvenienceRepository;
import br.edu.ifgoiano.hotel.service.ConvenienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConvenienceServiceImpl implements ConvenienceService {

    @Autowired
    private ConvenienceRepository convenienceRepository;

    @Override
    public Convenience create(Convenience convenience) {
        return convenienceRepository.save(convenience);
    }
}
