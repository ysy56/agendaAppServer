package com.nbcamp.agendaappserver.repository;

import com.nbcamp.agendaappserver.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
