package com.nbcamp.agendaappserver.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.parameters.P;

import java.time.LocalDate;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class) // 자동으로 시간을 넣어주는 기능을 하게 해 줌
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nickname;

    @Size(min = 4, max = 10, message = "4자 이상 10자 이하로 입력해주세요.")
    @Pattern(regexp = "^[a-z0-9]*$", message = "알파벳 소문자, 숫자로만 입력해주세요.")
    @Column(nullable = false, unique = true)
    private String username;

    @Size(min = 8, max = 15, message = "8자 이상 15자 이하로 입력해주세요.")
    @Pattern(regexp = "^[A-Za-z0-9]*$", message = "알파벳 대소문자, 숫자로만 입력해주세요.")
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @CreatedDate
    @Column(updatable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate createdAt;

    public User(String nickname, String username, String password, UserRoleEnum role) {
        this.nickname = nickname;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}