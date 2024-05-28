package com.nbcamp.agendaappserver.entity;

import com.nbcamp.agendaappserver.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity // JPA가 관리할 수 있는 Entity 클래스 지정
@Getter
@EntityListeners(AuditingEntityListener.class) // 자동으로 시간을 넣어주는 기능을 하게 해 줌
@Table(name = "comment") // 매핑할 테이블의 이름을 지정
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "content")
    private String content;
    @Column(name = "manager")
    private String manager;

    @CreatedDate
    @Column(updatable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate writedAt;

    @ManyToOne
    @JoinColumn(name = "agenda_id")
    private Agenda agenda;

    @Builder
    public Comment(String content, String manager, Agenda agenda) {
        this.content = content;
        this.manager = manager;
        this.agenda = agenda;
    }

    public void update(CommentRequestDto requestDto) {
        this.content = requestDto.getContent();
    }
}
