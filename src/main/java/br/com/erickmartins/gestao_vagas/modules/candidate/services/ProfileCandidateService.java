package br.com.erickmartins.gestao_vagas.modules.candidate.services;

import br.com.erickmartins.gestao_vagas.exceptions.UserNotFoundException;
import br.com.erickmartins.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.erickmartins.gestao_vagas.modules.candidate.entities.CandidateEntity;
import br.com.erickmartins.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public ProfileCandidateResponseDTO getCandidateDetails() {
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        CandidateEntity candidate = candidateRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        return ProfileCandidateResponseDTO.builder()
                .id(candidate.getId())
                .name(candidate.getName())
                .description(candidate.getDescription())
                .email(candidate.getEmail())
                .username(candidate.getUsername())
                .build();
    }
}
