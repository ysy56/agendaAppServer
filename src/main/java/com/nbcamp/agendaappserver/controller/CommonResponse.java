package com.nbcamp.agendaappserver.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class CommonResponse<T> {
    private Integer statusCode;
    private String msg;
    private T data;
}
