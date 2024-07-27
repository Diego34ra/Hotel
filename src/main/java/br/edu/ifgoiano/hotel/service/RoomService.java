package br.edu.ifgoiano.hotel.service;

import br.edu.ifgoiano.hotel.controller.dto.request.RoomNoCommentOutputDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.RoomOutputDTO;
import br.edu.ifgoiano.hotel.model.Room;

import java.util.List;

public interface RoomService {
    RoomOutputDTO create(Room quarto);
    List<RoomNoCommentOutputDTO> findAll();
    RoomOutputDTO findById(Long id);
    RoomOutputDTO update(Long id,Room room);
    void delete(Long id);
}
