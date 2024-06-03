package com.nbcamp.agendaappserver.dto;

import com.nbcamp.agendaappserver.entity.User;
import lombok.Data;
import lombok.Getter;

@Getter
public class SignupResponseDto {
    private Long id;
    private String nickname;
    private String username;
    private String password;

    public SignupResponseDto(User user) {
        this.id = user.getId();
        this.nickname = user.getNickname();
        this.username = user.getUsername();
        this.password = user.getPassword();
    }
}
