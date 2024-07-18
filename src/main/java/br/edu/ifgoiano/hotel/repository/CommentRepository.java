package br.edu.ifgoiano.hotel.repository;

import br.edu.ifgoiano.hotel.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository <Comment,Long> {
    List<Comment> findAllByRoomId(Long roomId);
}
