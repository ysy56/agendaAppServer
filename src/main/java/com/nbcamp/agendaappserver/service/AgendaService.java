package com.nbcamp.agendaappserver.service;

import com.nbcamp.agendaappserver.dto.AgendaRequestDto;
import com.nbcamp.agendaappserver.dto.AgendaResponseDto;
import com.nbcamp.agendaappserver.entity.Agenda;
import com.nbcamp.agendaappserver.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
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

    @Transactional
    public AgendaResponseDto updateAgenda(Long id, AgendaRequestDto requestDto) {
        // 해당 일정이 DB에 존재하는지 확인
        Agenda agenda = findAgenda(id);

        if (agenda.getPsword() != null
                && !Objects.equals(agenda.getPsword(), requestDto.getPsword())) {
            throw new IllegalArgumentException("password mismatch");
        }

        // agenda 내용 수정
        agenda.update(requestDto);

        // Entity -> ResponseDto
        AgendaResponseDto agendaResponseDto = new AgendaResponseDto(agenda);

        return agendaResponseDto;
    }

    public Long deleteAgenda(Long id, AgendaRequestDto requestDto) {
        // 해당 일정이 DB에 존재하는지 확인
        Agenda agenda = findAgenda(id);

        if (agenda.getPsword() != null
                && !Objects.equals(agenda.getPsword(), requestDto.getPsword())) {
            throw new IllegalArgumentException("password mismatch");
        }

        // Agenda 삭제
        agendaRepository.delete(agenda);

        return id;
    }

    private Agenda findAgenda(Long id) {
        return agendaRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Agenda not found")
        );
    }
}
