package br.edu.ifgoiano.hotel.service.impl;

import br.edu.ifgoiano.hotel.controller.CommentController;
import br.edu.ifgoiano.hotel.controller.dto.mapper.MyModelMapper;
import br.edu.ifgoiano.hotel.controller.dto.request.CommentInputDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.CommentOutputDTO;
import br.edu.ifgoiano.hotel.controller.exception.ResourceNotFoundException;
import br.edu.ifgoiano.hotel.model.Comment;
import br.edu.ifgoiano.hotel.model.Room;
import br.edu.ifgoiano.hotel.model.User;
import br.edu.ifgoiano.hotel.repository.BookingRepository;
import br.edu.ifgoiano.hotel.repository.CommentRepository;
import br.edu.ifgoiano.hotel.service.CommentService;
import br.edu.ifgoiano.hotel.service.RoomService;
import br.edu.ifgoiano.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private MyModelMapper mapper;

    @Override
    public CommentOutputDTO create(CommentInputDTO comment, Long clientId, Long roomId) {
        var commentCreate = mapper.mapTo(comment,Comment.class);
        commentCreate.setClient(userService.findById(clientId));
        commentCreate.setRoom(roomService.findById(roomId));
        commentCreate.setDate(LocalDateTime.now());


        boolean bookingExists = bookingRepository.existsByClientAndRoom(commentCreate.getClient(), commentCreate.getRoom());

        if (!bookingExists)
            throw new ResourceNotFoundException("Cliente não fez uma reserva para este quarto.");


        return mapper.mapTo(commentRepository.save(commentCreate),CommentOutputDTO.class)
                .add(linkTo(methodOn(CommentController.class).findAll(roomId)).withRel("list-room-comments"));
    }

    @Override
    public List<CommentOutputDTO> findAllByRoomId(Long roomId) {
        return mapper.toList(commentRepository.findAllByRoomId(roomId),CommentOutputDTO.class);
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public CommentOutputDTO update(Long id, CommentInputDTO commentUpdate) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum comentário com esse id"));
        if(!commentUpdate.getText().isEmpty())
            comment.setText(commentUpdate.getText());
        return mapper.mapTo(commentRepository.save(comment),CommentOutputDTO.class);
    }

    @Override
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }
}
