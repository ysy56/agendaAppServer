package com.nbcamp.agendaappserver.dto;

import com.nbcamp.agendaappserver.entity.Agenda;
import lombok.Getter;

@Getter
public class AgendaRequestDto {
    private String title;
    private String content;
    private String manager;
    private String psword;

    public Agenda toEntity() {
        return Agenda.builder()
                .title(title)
                .content(content)
                .manager(manager)
                .psword(psword)
                .build();
    }
}
