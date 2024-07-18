package br.edu.ifgoiano.hotel.service.impl;

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
    @Override
    public Room create(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public Room findById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum quarto com esse Id."));
    }

    @Override
    public Room update(Long id,Room roomUpdate) {
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
        return roomRepository.save(room);
    }

    @Override
    public void delete(Long id) {
        var roomDelete = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum quarto com esse Id."));
        roomRepository.delete(roomDelete);
    }
}
