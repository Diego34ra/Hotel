package br.edu.ifgoiano.hotel.service.impl;

import br.edu.ifgoiano.hotel.controller.exception.ResourceNotFoundException;
import br.edu.ifgoiano.hotel.model.Comment;
import br.edu.ifgoiano.hotel.repository.BookingRepository;
import br.edu.ifgoiano.hotel.repository.CommentRepository;
import br.edu.ifgoiano.hotel.service.CommentService;
import br.edu.ifgoiano.hotel.service.RoomService;
import br.edu.ifgoiano.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

    @Override
    public Comment create(Comment comment) {
        comment.setClient(userService.findById(comment.getClient().getId()));
        comment.setRoom(roomService.findById(comment.getRoom().getId()));
        comment.setDate(LocalDateTime.now());

        boolean reservaExistente = bookingRepository.existsByClientAndRoom(comment.getClient(), comment.getRoom());

        if (!reservaExistente)
            throw new ResourceNotFoundException("Cliente não fez uma reserva para este quarto.");

        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> findAllByRoomId(Long roomId) {
        return commentRepository.findAllByRoomId(roomId);
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment update(Long id, Comment commentUpdate) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum comentário com esse id"));
        if(!commentUpdate.getText().isEmpty())
            comment.setText(commentUpdate.getText());
        return commentRepository.save(comment);
    }

    @Override
    public void delete(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado nenhum comentário com esse id"));
        commentRepository.delete(comment);
    }
}
