package br.edu.ifgoiano.hotel.controller;

import br.edu.ifgoiano.hotel.controller.dto.mapper.MyModelMapper;
import br.edu.ifgoiano.hotel.controller.dto.request.CommentOutputDTO;
import br.edu.ifgoiano.hotel.model.Comment;
import br.edu.ifgoiano.hotel.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/hotel/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private MyModelMapper mapper;

    @PostMapping
    public ResponseEntity<CommentOutputDTO> create(@RequestBody Comment comment){
        var commentCreated = commentService.create(comment);
        CommentOutputDTO commentCreatedDto = mapper.mapTo(commentCreated, CommentOutputDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentCreatedDto);
    }

    @GetMapping("/room/{roomId}")
    public ResponseEntity<List<CommentOutputDTO>> findAll(@PathVariable Long roomId){
            List<Comment> comments = commentService.findAllByRoomId(roomId);

        List<CommentOutputDTO> commentOutputDTOS = mapper.toList(comments, CommentOutputDTO.class);

        return ResponseEntity.status(HttpStatus.OK).body(commentOutputDTOS);
    }

    @PutMapping("{id}")
    public ResponseEntity<CommentOutputDTO> update(@PathVariable Long id,@RequestBody Comment comment){
        var commentUpdated = commentService.update(id,comment);
        CommentOutputDTO commentUpdatedDto = mapper.mapTo(commentUpdated, CommentOutputDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(commentUpdatedDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        commentService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
