package br.edu.ifgoiano.hotel.service;

import br.edu.ifgoiano.hotel.controller.dto.request.roomDTO.RoomInputDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.roomDTO.RoomNoCommentOutputDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.roomDTO.RoomOutputDTO;
import br.edu.ifgoiano.hotel.model.Room;
import br.edu.ifgoiano.hotel.model.RoomType;

import java.util.List;

public interface RoomService {
    RoomOutputDTO create(RoomInputDTO quarto);
    List<RoomNoCommentOutputDTO> findAll();
    List<RoomNoCommentOutputDTO> findRooms(RoomType type, Integer capacity, Boolean ascending, Boolean available);
    RoomOutputDTO findOneById(Long id);
    Room findById(Long id);
    RoomNoCommentOutputDTO findByIdWithoutComment(Long id);
    RoomOutputDTO update(Long id,Room room);
    void delete(Long id);
}
