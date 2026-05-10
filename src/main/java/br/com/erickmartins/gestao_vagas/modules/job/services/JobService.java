package br.com.erickmartins.gestao_vagas.modules.job.services;

import br.com.erickmartins.gestao_vagas.exceptions.CompanyNotFoundException;
import br.com.erickmartins.gestao_vagas.modules.company.repositories.CompanyRepository;
import br.com.erickmartins.gestao_vagas.modules.job.dto.JobDTO;
import br.com.erickmartins.gestao_vagas.modules.job.entities.JobEntity;
import br.com.erickmartins.gestao_vagas.modules.job.mapper.JobMapper;
import br.com.erickmartins.gestao_vagas.modules.job.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public JobEntity create(JobDTO jobDTO, UUID companyId) {
        companyRepository.findById(companyId).orElseThrow(CompanyNotFoundException::new);
        JobEntity jobEntity = JobMapper.toEntity(jobDTO, companyId);

        return jobRepository.save(jobEntity);
    }

    public void createForView(JobDTO jobDTO, UUID companyId) {
        create(jobDTO, companyId);
    }

    public List<JobDTO> getAvailableJobs(UUID idCandidate, String filter) {
        List<JobEntity> jobs = jobRepository.findAvailableJobs(idCandidate, filter);

        return jobs.stream()
                .map(JobMapper::toDTO)
                .toList();
    }
}
