package com.nbcamp.agendaappserver.service;

import com.nbcamp.agendaappserver.dto.AgendaRequestDto;
import com.nbcamp.agendaappserver.dto.AgendaResponseDto;
import com.nbcamp.agendaappserver.entity.Agenda;
import com.nbcamp.agendaappserver.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaService {
    private AgendaRepository agendaRepository;

    @Autowired
    public AgendaService(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    public AgendaResponseDto createAgenda(AgendaRequestDto requestDto) {
        // RequestDto -> Entity
        Agenda agenda = new Agenda(requestDto);

        // DB 저장
        Agenda savedAgenda = agendaRepository.save(agenda);

        // Entity -> ResponseDto
        AgendaResponseDto agendaResponseDto = new AgendaResponseDto(savedAgenda);

        return agendaResponseDto;
    }

    public AgendaResponseDto getAgenda(Long id) {
        return agendaRepository.findById(id).map(AgendaResponseDto::new).orElse(null);
    }
}
