package com.nbcamp.agendaappserver.service;

import com.nbcamp.agendaappserver.dto.AgendaRequestDto;
import com.nbcamp.agendaappserver.dto.AgendaResponseDto;
import com.nbcamp.agendaappserver.entity.Agenda;
import com.nbcamp.agendaappserver.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        // DB 조회
        return agendaRepository.findById(id).map(AgendaResponseDto::new).orElse(null);
    }

    public List<AgendaResponseDto> getAllAgendas(String sort, String order) {
        // DB 조회
        Sort.Direction direction = order.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sortBy = Sort.by(direction, sort);

        List<Agenda> agendas = agendaRepository.findAll(sortBy);
        return agendas.stream().map(AgendaResponseDto::new).collect(Collectors.toList());
    }
}
