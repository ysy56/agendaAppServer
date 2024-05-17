package com.nbcamp.agendaappserver.controller;

import com.nbcamp.agendaappserver.dto.AgendaRequestDto;
import com.nbcamp.agendaappserver.dto.AgendaResponseDto;
import com.nbcamp.agendaappserver.service.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public AgendaResponseDto createAgenda(@RequestBody AgendaRequestDto requestDto) {
        return agendaService.createAgenda(requestDto);
    }

    @GetMapping("/agenda/{id}")
    public AgendaResponseDto getAgenda(@PathVariable Long id) {
        return agendaService.getAgenda(id);
    }

    @GetMapping("/agendas")
    public List<AgendaResponseDto> getAllAgendas(@RequestParam(value = "sort", required = false, defaultValue = "createdAt") String sort,
                                                 @RequestParam(value = "order", required = false, defaultValue = "desc") String order) {
        return agendaService.getAllAgendas(sort, order);
    }
}
