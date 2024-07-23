package br.edu.ifgoiano.hotel.service;

import br.edu.ifgoiano.hotel.controller.dto.request.CommentOutputDTO;
import br.edu.ifgoiano.hotel.model.Comment;

import java.util.List;

public interface CommentService {

    CommentOutputDTO create (Comment comment);

    List<CommentOutputDTO> findAllByRoomId(Long roomId);

    List<Comment> findAll();

    CommentOutputDTO update (Long id,Comment comment);

    void delete (Long id);
}
