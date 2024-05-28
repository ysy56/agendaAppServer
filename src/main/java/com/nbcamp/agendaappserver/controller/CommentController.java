package com.nbcamp.agendaappserver.controller;

import com.nbcamp.agendaappserver.CommonResponse;
import com.nbcamp.agendaappserver.dto.AgendaRequestDto;
import com.nbcamp.agendaappserver.dto.AgendaResponseDto;
import com.nbcamp.agendaappserver.dto.CommentRequestDto;
import com.nbcamp.agendaappserver.dto.CommentResponseDto;
import com.nbcamp.agendaappserver.entity.Agenda;
import com.nbcamp.agendaappserver.entity.Comment;
import com.nbcamp.agendaappserver.service.AgendaService;
import com.nbcamp.agendaappserver.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;
    private final AgendaService agendaService;

    @Autowired
    public CommentController(CommentService commentService, AgendaService agendaService) {
        this.commentService = commentService;
        this.agendaService = agendaService;
    }

    @PostMapping("/agenda/{agendaId}/comment")
    public ResponseEntity<CommonResponse<CommentResponseDto>> createComment(@PathVariable Long agendaId, @RequestBody CommentRequestDto requestDto) {
        Agenda agenda = agendaService.getAgenda(agendaId);
        Comment comment = commentService.createComment(agenda, requestDto);
        CommentResponseDto response = new CommentResponseDto(comment);
        return ResponseEntity.ok()
                .body(CommonResponse.<CommentResponseDto>builder()
                        .statusCode(HttpStatus.OK.value())
                        .msg("생성이 완료 되었습니다.")
                        .data(response)
                        .build());
    }

    @GetMapping("/agenda/{agendaId}/comment/{commentId}")
    public ResponseEntity<CommonResponse<CommentResponseDto>> updateComment(
            @PathVariable Long agendaId,
            @PathVariable Long commentId,
            @RequestBody CommentRequestDto requestDto)
    {
        Agenda agenda = agendaService.getAgenda(agendaId);
        Comment comment = commentService.updateComment(commentId, requestDto);
        CommentResponseDto response = new CommentResponseDto(comment);
        return ResponseEntity.ok()
                .body(CommonResponse.<CommentResponseDto>builder()
                        .statusCode(HttpStatus.OK.value())
                        .msg("수정이 완료 되었습니다.")
                        .data(response)
                        .build());
    }

    @DeleteMapping("/agenda/{agendaId}/comment/{commentId}")
    public ResponseEntity<CommonResponse> deleteComment(
            @PathVariable Long agendaId,
            @PathVariable Long commentId,
            @RequestBody CommentRequestDto requestDto)
    {
        Agenda agenda = agendaService.getAgenda(agendaId);
        commentService.deleteComment(commentId, requestDto);
        return ResponseEntity.ok()
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .msg("삭제가 완료 되었습니다.")
                        .build());
    }
}
