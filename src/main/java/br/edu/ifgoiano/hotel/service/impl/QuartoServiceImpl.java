package br.edu.ifgoiano.hotel.service.impl;

import br.edu.ifgoiano.hotel.controller.exception.ResourceNotFoundException;
import br.edu.ifgoiano.hotel.model.Quarto;
import br.edu.ifgoiano.hotel.repository.QuartoRepository;
import br.edu.ifgoiano.hotel.service.QuartoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuartoServiceImpl implements QuartoService {

    @Autowired
    private QuartoRepository quartoRepository;
    @Override
    public Quarto create(Quarto quarto) {
        return quartoRepository.save(quarto);
    }

    @Override
    public List<Quarto> findAll() {
        return quartoRepository.findAll();
    }

    @Override
    public Quarto findById(Long id) {
        return quartoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum quarto com esse Id."));
    }

    @Override
    public void delete(Long id) {
        var quartoDelete = quartoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum quarto com esse Id."));
        quartoRepository.delete(quartoDelete);
    }
}
