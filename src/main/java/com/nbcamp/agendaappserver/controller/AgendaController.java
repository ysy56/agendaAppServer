package com.nbcamp.agendaappserver.controller;

import com.nbcamp.agendaappserver.CommonResponse;
import com.nbcamp.agendaappserver.dto.AgendaRequestDto;
import com.nbcamp.agendaappserver.dto.AgendaResponseDto;
import com.nbcamp.agendaappserver.entity.Agenda;
import com.nbcamp.agendaappserver.service.AgendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@Tag(name = "Response Agenda", description = "Response Agenda API")
public class AgendaController {
    private final AgendaService agendaService;

    @Autowired
    public AgendaController(AgendaService agendaService) {
        this.agendaService = agendaService;
    }

    @PostMapping("/agenda")
    @Operation(summary = "일정 작성", description = "일정을 작성할 때 사용하는 API")
    @ApiResponse(responseCode = "200", description = "생성이 완료 되었습니다.", content = @Content(mediaType = "application/json"))
    public ResponseEntity<CommonResponse<AgendaResponseDto>> createAgenda(@RequestBody AgendaRequestDto requestDto) {
        Agenda agenda = agendaService.createAgenda(requestDto);
        AgendaResponseDto response = new AgendaResponseDto(agenda);
        return ResponseEntity.ok()
                .body(CommonResponse.<AgendaResponseDto>builder()
                        .statusCode(HttpStatus.OK.value())
                        .msg("생성이 완료 되었습니다.")
                        .data(response)
                        .build());
    }

    @GetMapping("/agenda/{id}")
    @Operation(summary = "선택한 일정 조회", description = "선택한 일정을 조회 할 때 사용하는 API")
    @ApiResponse(responseCode = "200", description = "단건 조회가 완료 되었습니다.", content = @Content(mediaType = "application/json"))
    @Parameter(name = "id", description = "일정 아이디", example = "3")
    public ResponseEntity<CommonResponse<AgendaResponseDto>> getAgenda(@PathVariable Long id) {
        Agenda agenda = agendaService.getAgenda(id);
        AgendaResponseDto response = new AgendaResponseDto(agenda);
        return ResponseEntity.ok()
                .body(CommonResponse.<AgendaResponseDto>builder()
                        .statusCode(HttpStatus.OK.value())
                        .msg("단건 조회가 완료 되었습니다.")
                        .data(response)
                        .build());
    }

    @GetMapping("/agendas")
    @Operation(summary = "일정 조회", description = "전체 일정을 조회 할 때 사용하는 API")
    @ApiResponse(responseCode = "200", description = "목록 조회가 완료 되었습니다.", content = @Content(mediaType = "application/json"))
    public ResponseEntity<CommonResponse<List<AgendaResponseDto>>> getAgendas() {
        List<Agenda> agendas = agendaService.getAgendas();
        List<AgendaResponseDto> response = agendas.stream()
                .map(AgendaResponseDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok()
                .body(CommonResponse.<List<AgendaResponseDto>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .msg("목록 조회가 완료 되었습니다.")
                        .data(response)
                        .build());
    }

    @PutMapping("/agenda/{id}")
    @Operation(summary = "선택한 일정 수정", description = "선택한 일정을 수정 할 때 사용하는 API")
    @ApiResponse(responseCode = "200", description = "수정이 완료 되었습니다.", content = @Content(mediaType = "application/json"))
    @Parameter(name = "id", description = "일정 아이디", example = "3")
    public ResponseEntity<CommonResponse<AgendaResponseDto>> updateAgenda(@PathVariable Long id, @RequestBody AgendaRequestDto requestDto) {
        Agenda agenda = agendaService.updateAgenda(id, requestDto);
        AgendaResponseDto response = new AgendaResponseDto(agenda);
        return ResponseEntity.ok()
                .body(CommonResponse.<AgendaResponseDto>builder()
                        .statusCode(HttpStatus.OK.value())
                        .msg("수정이 완료 되었습니다.")
                        .data(response)
                        .build());
    }

    @DeleteMapping("/agenda/{id}")
    @Operation(summary = "선택한 일정 삭제", description = "선택한 일정을 삭제 할 때 사용하는 API")
    @ApiResponse(responseCode = "200", description = "삭제가 완료 되었습니다.", content = @Content(mediaType = "application/json"))
    @Parameter(name = "id", description = "일정 아이디", example = "3")
    public ResponseEntity<CommonResponse> deleteAgenda(@PathVariable Long id, @RequestBody AgendaRequestDto requestDto) {
        agendaService.deleteAgenda(id, requestDto);
        return ResponseEntity.ok()
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .msg("삭제가 완료 되었습니다.")
                        .build());
    }
}
