package br.edu.ifgoiano.hotel.service;

import br.edu.ifgoiano.hotel.controller.dto.request.roomDTO.RoomInputDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.roomDTO.RoomNoCommentOutputDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.roomDTO.RoomOutputDTO;
import br.edu.ifgoiano.hotel.model.Room;

import java.util.List;

public interface RoomService {
    RoomOutputDTO create(RoomInputDTO quarto);
    List<RoomNoCommentOutputDTO> findAll();
    RoomOutputDTO findById(Long id);
    RoomNoCommentOutputDTO findByIdWithoutComment(Long id);
    RoomOutputDTO update(Long id,Room room);
    void delete(Long id);
}
