package br.com.erickmartins.gestao_vagas.modules.candidate.mapper;

import br.com.erickmartins.gestao_vagas.modules.candidate.dto.ProfileCandidateDTO;
import br.com.erickmartins.gestao_vagas.modules.candidate.entities.CandidateEntity;

public class CandidateMapper {

    public static ProfileCandidateDTO toDTO(CandidateEntity entity) {
        return ProfileCandidateDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .description(entity.getDescription())
                .username(entity.getUsername())
                .build();
    }
}
