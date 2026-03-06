package br.com.erickmartins.gestao_vagas.modules.company.services;

import br.com.erickmartins.gestao_vagas.modules.company.entities.JobEntity;
import br.com.erickmartins.gestao_vagas.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public JobEntity execute(JobEntity jobEntity) {
        return this.jobRepository.save(jobEntity);
    }
}
