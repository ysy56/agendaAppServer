package com.nbcamp.agendaappserver.entity;

import com.nbcamp.agendaappserver.dto.AgendaRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity // JPA가 관리할 수 있는 Entity 클래스 지정
@Getter
@EntityListeners(AuditingEntityListener.class) // 자동으로 시간을 넣어주는 기능을 하게 해 줌
@Table(name = "agenda") // 매핑할 테이블의 이름을 지정
@NoArgsConstructor
public class Agenda{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false)
    @Size(max = 200, message = "제목은 200자 이내로 작성해주세요.")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "manager")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String manager;
    @Column(name = "psword", nullable = false)
    private String psword;

    @CreatedDate
    @Column(updatable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate createdAt;

    @Builder
    public Agenda(String title, String content, String manager, String psword) {
        this.title = title;
        this.content = content;
        this.manager = manager;
        this.psword = psword;
    }

    public void update(AgendaRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.manager = requestDto.getManager();
    }

}
