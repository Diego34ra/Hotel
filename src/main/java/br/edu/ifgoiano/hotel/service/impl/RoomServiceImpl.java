package br.edu.ifgoiano.hotel.service.impl;

import br.edu.ifgoiano.hotel.controller.RoomController;
import br.edu.ifgoiano.hotel.controller.dto.mapper.MyModelMapper;
import br.edu.ifgoiano.hotel.controller.dto.request.roomDTO.RoomInputDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.roomDTO.RoomNoCommentOutputDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.roomDTO.RoomOutputDTO;
import br.edu.ifgoiano.hotel.controller.exception.ResourceNotFoundException;
import br.edu.ifgoiano.hotel.model.Room;
import br.edu.ifgoiano.hotel.repository.RoomRepository;
import br.edu.ifgoiano.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private MyModelMapper mapper;
    @Override
    public RoomOutputDTO create(RoomInputDTO room) {
        var roomCreate = mapper.mapTo(room,Room.class);
        return mapper.mapTo(roomCreate,RoomOutputDTO.class)
                .add(linkTo(methodOn(RoomController.class).findAll()).withRel("list-rooms"))
                .add(linkTo(methodOn(RoomController.class).findById(roomCreate.getId())).withSelfRel());
    }

    @Override
    public List<RoomNoCommentOutputDTO> findAll() {
        return mapper.toList(roomRepository.findAll(),RoomNoCommentOutputDTO.class)
                .stream().map(
                        outputDTO -> outputDTO.add(linkTo(methodOn(RoomController.class)
                                .findById(outputDTO.getKey())).withSelfRel())
                ).toList();
    }

    @Override
    public RoomOutputDTO findById(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum quarto com esse Id."));
        return mapper.mapTo(room,RoomOutputDTO.class)
                .add(linkTo(methodOn(RoomController.class).findAll()).withRel("list-rooms"));
    }

    @Override
    public RoomNoCommentOutputDTO findByIdWithoutComment(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum quarto com esse Id."));
        return mapper.mapTo(room,RoomNoCommentOutputDTO.class);
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
        return mapper.mapTo(roomRepository.save(room),RoomOutputDTO.class)
                .add(linkTo(methodOn(RoomController.class).findAll()).withRel("list-rooms"))
                .add(linkTo(methodOn(RoomController.class).findById(room.getId())).withSelfRel());
    }

    @Override
    public void delete(Long id) {
        roomRepository.deleteById(id);
    }
}
