package com.nbcamp.agendaappserver.dto;

import com.nbcamp.agendaappserver.entity.Agenda;
import lombok.Getter;

import java.time.LocalDate;


@Getter
public class AgendaResponseDto {
    private Long id;
    private String title;
    private String content;
    private String manager;
    private LocalDate createdAt;

    public AgendaResponseDto(Agenda agenda) {
        this.id = agenda.getId();
        this.title = agenda.getTitle();
        this.content = agenda.getContent();
        this.manager = agenda.getManager();
        this.createdAt = agenda.getCreatedAt();
    }
}
