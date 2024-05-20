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

import static java.util.stream.Collectors.toList;

@Service
public class AgendaService {
    private AgendaRepository agendaRepository;

    @Autowired
    public AgendaService(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    public Agenda createAgenda(AgendaRequestDto requestDto) {
        // RequestDto -> Entity
        Agenda agenda = requestDto.toEntity();
        return agendaRepository.save(agenda);
    }

    public Agenda getAgenda(Long id) {
        // DB 조회
        return agendaRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public List<Agenda> getAgendas() {
        // DB 조회
        return agendaRepository.findAll(Sort.by("createdAt").descending());
    }

    @Transactional
    public Agenda updateAgenda(Long id, AgendaRequestDto requestDto) {
        // 해당 일정이 DB에 존재하는지 확인
        Agenda agenda = checkPWAndGetAgenda(id, requestDto.getPsword());

        agenda.setTitle(requestDto.getTitle());
        agenda.setContent(requestDto.getContent());
        agenda.setManager(requestDto.getManager());

        return agendaRepository.save(agenda);
    }

    public void deleteAgenda(Long id, AgendaRequestDto requestDto) {
        // 해당 일정이 DB에 존재하는지 확인
        Agenda agenda = checkPWAndGetAgenda(id, requestDto.getPsword());

        // Agenda 삭제
        agendaRepository.delete(agenda);
    }

    public Agenda checkPWAndGetAgenda(Long id, String psword) {
        Agenda agenda = getAgenda(id);

        // 비밀번호 체크
        if (agenda.getPsword() != null
            && !Objects.equals(agenda.getPsword(), psword)) {
            throw new IllegalArgumentException("password mismatch");
        }
        return agenda;
    }
}
