package com.nbcamp.agendaappserver.service;

import com.nbcamp.agendaappserver.dto.CommentRequestDto;
import com.nbcamp.agendaappserver.entity.Agenda;
import com.nbcamp.agendaappserver.entity.Comment;
import com.nbcamp.agendaappserver.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

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

    public Comment getComment(Long commentId) {
        // DB 조회
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글은 존재하지 않습니다."));
    }

    @Transactional
    public Comment updateComment(Long commentId, CommentRequestDto requestDto) {
        // 해당 일정이 DB에 존재하는지 확인
        Comment comment = checkManagerAndGetComment(commentId, requestDto.getManager());

        comment.update(requestDto);

        return commentRepository.save(comment);
    }

    public void deleteComment(Long commentId, CommentRequestDto requestDto) {
        // 해당 일정이 DB에 존재하는지 확인
        Comment comment = checkManagerAndGetComment(commentId, requestDto.getManager());

        // Agenda 삭제
        commentRepository.delete(comment);
    }

    public Comment checkManagerAndGetComment(Long commentId, String manager) {
        Comment comment = getComment(commentId);

        // 작성자 체크
        if (comment.getManager() != null
                && !Objects.equals(manager, comment.getManager())) {
            throw new IllegalArgumentException("댓글 작성자(담당자)가 일치하지 않습니다.");
        }

        return comment;
    }

}
