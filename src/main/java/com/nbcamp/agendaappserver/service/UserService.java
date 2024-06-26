package com.nbcamp.agendaappserver.service;

import com.nbcamp.agendaappserver.dto.LoginRequestDto;
import com.nbcamp.agendaappserver.dto.SignupRequestDto;
import com.nbcamp.agendaappserver.entity.User;
import com.nbcamp.agendaappserver.entity.UserRoleEnum;
import com.nbcamp.agendaappserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    // ADMIN_TOKEN
    @Value("${admin.token}")
    private String ADMIN_TOKEN;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User signup(SignupRequestDto requestDto) {
        String nickname = requestDto.getNickname();
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        // 회원 중복 확인
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        // 사용자 등록
        User user = new User(nickname, username, password, role);


        return userRepository.save(user);
    }

    public void login(LoginRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        // 회원 존재 확인
        Optional<User> checkUser = userRepository.findByUsername(username);
        if (checkUser.isPresent()) {
            User user = checkUser.get();
            if (!user.getPassword().equals(password)) {
                throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
            }
        } else {
            throw new IllegalArgumentException("등록된 사용자가 없습니다.");
        }
    }
}