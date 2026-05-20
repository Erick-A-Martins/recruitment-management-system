package br.com.erickmartins.gestao_vagas.modules.job.mapper;

import br.com.erickmartins.gestao_vagas.modules.job.dto.JobDTO;
import br.com.erickmartins.gestao_vagas.modules.job.entities.JobEntity;

import java.util.UUID;

public class JobMapper {

    public static JobDTO toDTO(JobEntity entity) {
        return JobDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .level(entity.getLevel())
                .benefits(entity.getBenefits())
                .build();
    }

    public static JobEntity toEntity(JobDTO dto, UUID companyId) {
        return JobEntity.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .benefits(dto.getBenefits())
                .level(dto.getLevel())
                .companyId(companyId)
                .build();
    }
}
