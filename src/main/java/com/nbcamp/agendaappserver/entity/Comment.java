package com.nbcamp.agendaappserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity // JPA가 관리할 수 있는 Entity 클래스 지정
@Getter
@EntityListeners(AuditingEntityListener.class) // 자동으로 시간을 넣어주는 기능을 하게 해 줌
@Table(name = "comment") // 매핑할 테이블의 이름을 지정
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
