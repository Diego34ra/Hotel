package br.edu.ifgoiano.hotel.service.impl;

import br.edu.ifgoiano.hotel.controller.dto.mapper.MyModelMapper;
import br.edu.ifgoiano.hotel.controller.dto.request.RoomOutputDTO;
import br.edu.ifgoiano.hotel.controller.exception.ResourceNotFoundException;
import br.edu.ifgoiano.hotel.model.Room;
import br.edu.ifgoiano.hotel.repository.RoomRepository;
import br.edu.ifgoiano.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private MyModelMapper mapper;
    @Override
    public RoomOutputDTO create(Room room) {
        return mapper.mapTo(roomRepository.save(room),RoomOutputDTO.class);
    }

    @Override
    public List<RoomOutputDTO> findAll() {
        return mapper.toList(roomRepository.findAll(),RoomOutputDTO.class);
    }

    @Override
    public RoomOutputDTO findById(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum quarto com esse Id."));
        return mapper.mapTo(room,RoomOutputDTO.class);
    }

    @Override
    public RoomOutputDTO update(Long id,Room roomUpdate) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum quarto com esse Id."));
        if(roomUpdate.getCapacity() != null)
            room.setCapacity(roomUpdate.getCapacity());
        if(roomUpdate.getPrice() != null)
            room.setPrice(roomUpdate.getPrice());
        if(roomUpdate.getFloor() != null)
            room.setFloor(roomUpdate.getFloor());
        if(roomUpdate.getType() != null)
            room.setType(roomUpdate.getType());
        if(roomUpdate.getAvailable() != null)
            room.setAvailable(roomUpdate.getAvailable());
        if(roomUpdate.getDescription() != null)
            room.setDescription(roomUpdate.getDescription());
        return mapper.mapTo(roomRepository.save(room),RoomOutputDTO.class);
    }

    @Override
    public void delete(Long id) {
        var roomDelete = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum quarto com esse Id."));
        roomRepository.delete(roomDelete);
    }
}
