package com.nbcamp.agendaappserver.dto;

import com.nbcamp.agendaappserver.entity.Comment;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CommentResponseDto {
    @NotEmpty
    private Long id;
    private String content;
    private String manager;
    private LocalDate writedAt;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.manager = comment.getManager();
        this.writedAt = comment.getWritedAt();
    }
}
