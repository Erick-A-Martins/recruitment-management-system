package br.com.erickmartins.gestao_vagas.modules.candidate.services;

import br.com.erickmartins.gestao_vagas.exceptions.UserNotFoundException;
import br.com.erickmartins.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.erickmartins.gestao_vagas.modules.candidate.entities.CandidateEntity;
import br.com.erickmartins.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO execute(UUID candidateId) {
        CandidateEntity candidate = candidateRepository.findById(candidateId)
                .orElseThrow(UserNotFoundException::new);

        return ProfileCandidateResponseDTO.builder()
                .description(candidate.getDescription())
                .username(candidate.getUsername())
                .email(candidate.getEmail())
                .name(candidate.getName())
                .id(candidate.getId())
                .build();
    }
}
