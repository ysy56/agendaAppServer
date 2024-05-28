package com.nbcamp.agendaappserver.service;

import com.nbcamp.agendaappserver.dto.AgendaRequestDto;
import com.nbcamp.agendaappserver.dto.CommentRequestDto;
import com.nbcamp.agendaappserver.dto.CommentResponseDto;
import com.nbcamp.agendaappserver.entity.Agenda;
import com.nbcamp.agendaappserver.entity.Comment;
import com.nbcamp.agendaappserver.repository.AgendaRepository;
import com.nbcamp.agendaappserver.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment createComment(Agenda agenda, CommentRequestDto requestDto) {
        // RequestDto -> Entity
        Comment comment = new Comment(requestDto.getContent(), requestDto.getManager(), agenda);
        return commentRepository.save(comment);
    }
}
