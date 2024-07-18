package br.edu.ifgoiano.hotel.service;

import br.edu.ifgoiano.hotel.model.Comment;

import java.util.List;

public interface CommentService {

    Comment create (Comment comment);

    List<Comment> findAllByRoomId(Long roomId);

    List<Comment> findAll();

    Comment update (Long id,Comment comment);

    void delete (Long id);
}
