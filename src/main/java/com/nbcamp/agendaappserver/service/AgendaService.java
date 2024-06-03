package com.nbcamp.agendaappserver.service;

import com.nbcamp.agendaappserver.dto.AgendaRequestDto;
import com.nbcamp.agendaappserver.entity.Agenda;
import com.nbcamp.agendaappserver.repository.AgendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class AgendaService {
    private AgendaRepository agendaRepository;

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
        return agendaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정은 존재하지 않습니다."));
    }

    public List<Agenda> getAgendas() {
        // DB 조회
        return agendaRepository.findAll(Sort.by("createdAt").descending());
    }

    @Transactional
    public Agenda updateAgenda(Long id, AgendaRequestDto requestDto) {
        // 해당 일정이 DB에 존재하는지 확인
        Agenda agenda = checkPWAndGetAgenda(id, requestDto.getPsword());

        agenda.update(requestDto);

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
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return agenda;
    }
}
