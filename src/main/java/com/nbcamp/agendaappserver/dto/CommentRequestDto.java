package com.nbcamp.agendaappserver.dto;

import com.nbcamp.agendaappserver.entity.Comment;
import lombok.Getter;

@Getter
public class CommentRequestDto {
    private String content;
    private String manager;

    public Comment toEntity() {
        return Comment.builder()
                .content(content)
                .manager(manager)
                .build();
    }
}
