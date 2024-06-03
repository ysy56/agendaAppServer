package com.nbcamp.agendaappserver.controller;

import com.nbcamp.agendaappserver.dto.LoginRequestDto;
import com.nbcamp.agendaappserver.dto.SignupRequestDto;
import com.nbcamp.agendaappserver.dto.SignupResponseDto;
import com.nbcamp.agendaappserver.entity.User;
import com.nbcamp.agendaappserver.jwt.JwtUtil;
import com.nbcamp.agendaappserver.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/user/signup")
    public ResponseEntity<CommonResponse<SignupResponseDto>> signup(@Valid @RequestBody SignupRequestDto requestDto) {
        User user = userService.signup(requestDto);
        SignupResponseDto response = new SignupResponseDto(user);
        return ResponseEntity.ok()
                .body(CommonResponse.<SignupResponseDto>builder()
                        .statusCode(HttpStatus.OK.value())
                        .msg("회원가입이 완료 되었습니다.")
                        .data(response)
                        .build());
    }

    @GetMapping("/user/login")
    public ResponseEntity<CommonResponse> login(@Valid @RequestBody LoginRequestDto requestDto) {
        userService.login(requestDto);

        return ResponseEntity.ok()
                .body(CommonResponse.<SignupResponseDto>builder()
                        .statusCode(HttpStatus.OK.value())
                        .msg("로그인이 완료 되었습니다.")
                        .build());
    }

}