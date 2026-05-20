package br.com.erickmartins.gestao_vagas.modules.job.services;

import br.com.erickmartins.gestao_vagas.modules.job.dto.JobDTO;
import br.com.erickmartins.gestao_vagas.modules.job.entities.JobEntity;
import br.com.erickmartins.gestao_vagas.modules.job.mapper.JobMapper;
import br.com.erickmartins.gestao_vagas.modules.job.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListAllJobsByFilterService {

    @Autowired
    private JobRepository jobRepository;

    public List<JobDTO> execute(String filter) {
        List<JobEntity> jobs = jobRepository.findByDescriptionContainingIgnoreCase(filter);

        return jobs.stream()
                .map(JobMapper::toDTO)
                .toList();
    }
}
