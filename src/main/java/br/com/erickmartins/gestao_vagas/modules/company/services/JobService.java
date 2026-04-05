package br.com.erickmartins.gestao_vagas.modules.company.services;

import br.com.erickmartins.gestao_vagas.exceptions.CompanyNotFoundException;
import br.com.erickmartins.gestao_vagas.modules.company.entities.JobEntity;
import br.com.erickmartins.gestao_vagas.modules.company.repositories.CompanyRepository;
import br.com.erickmartins.gestao_vagas.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public JobEntity execute(JobEntity jobEntity) {
        companyRepository.findById(jobEntity.getCompanyId()).orElseThrow(CompanyNotFoundException::new);
        return this.jobRepository.save(jobEntity);
    }
}
