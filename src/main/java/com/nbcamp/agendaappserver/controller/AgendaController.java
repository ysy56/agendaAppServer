package com.nbcamp.agendaappserver.controller;

import com.nbcamp.agendaappserver.CommonResponse;
import com.nbcamp.agendaappserver.dto.AgendaRequestDto;
import com.nbcamp.agendaappserver.dto.AgendaResponseDto;
import com.nbcamp.agendaappserver.entity.Agenda;
import com.nbcamp.agendaappserver.service.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AgendaController {
    private final AgendaService agendaService;

    @Autowired
    public AgendaController(AgendaService agendaService) {
        this.agendaService = agendaService;
    }

    @PostMapping("/agenda")
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
    public AgendaResponseDto getAgenda(@PathVariable Long id) {
        return agendaService.getAgenda(id);
    }

    @GetMapping("/agendas")
    public List<AgendaResponseDto> getAgendas() {
        return agendaService.getAgendas();
    }

    @PutMapping("/agenda/{id}")
    public AgendaResponseDto updateAgenda(@PathVariable Long id, @RequestBody AgendaRequestDto requestDto) {
        return agendaService.updateAgenda(id, requestDto);
    }

    @DeleteMapping("/agenda/{id}")
    public Long deleteAgenda(@PathVariable Long id, @RequestBody AgendaRequestDto requestDto) {
        return agendaService.deleteAgenda(id, requestDto);
    }
}
