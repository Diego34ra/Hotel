package br.edu.ifgoiano.hotel.service;

import br.edu.ifgoiano.hotel.controller.dto.request.CommentInputDTO;
import br.edu.ifgoiano.hotel.controller.dto.request.CommentOutputDTO;
import br.edu.ifgoiano.hotel.model.Comment;

import java.util.List;

public interface CommentService {

    CommentOutputDTO create (CommentInputDTO comment, Long clientId, Long roomId);

    List<CommentOutputDTO> findAllByRoomId(Long roomId);

    List<Comment> findAll();

    CommentOutputDTO update (Long id,CommentInputDTO comment);

    void delete (Long id);
}
