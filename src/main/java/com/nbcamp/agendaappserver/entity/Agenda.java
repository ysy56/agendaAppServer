package com.nbcamp.agendaappserver.entity;

import com.nbcamp.agendaappserver.dto.AgendaRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private String title;
    @Column(name = "content", nullable = false)
    private String content;
    @Column(name = "manager", nullable = false)
    private String manager;
    @Column(name = "psword", nullable = false)
    private String psword;

    @CreatedDate
    @Column(updatable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate createdAt;

    public Agenda(AgendaRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.manager = requestDto.getManager();
        this.psword = requestDto.getPsword();
    }

    public void update(AgendaRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.manager = requestDto.getManager();
    }
}
