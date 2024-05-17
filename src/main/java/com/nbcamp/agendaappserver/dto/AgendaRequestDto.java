package com.nbcamp.agendaappserver.dto;

import lombok.Getter;

@Getter
public class AgendaRequestDto {
    private String title;
    private String content;
    private String manager;
    private String psword;
}
