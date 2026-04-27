package br.com.erickmartins.gestao_vagas.modules.job.services;

import br.com.erickmartins.gestao_vagas.exceptions.JobNotFoundException;
import br.com.erickmartins.gestao_vagas.exceptions.UserNotFoundException;
import br.com.erickmartins.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import br.com.erickmartins.gestao_vagas.modules.job.entities.ApplyJobEntity;
import br.com.erickmartins.gestao_vagas.modules.job.repositories.ApplyJobRepository;
import br.com.erickmartins.gestao_vagas.modules.job.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApplyJobCandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplyJobRepository applyJobRepository;

    public ApplyJobEntity execute(UUID idCandidate, UUID idJob) {
        candidateRepository.findById(idCandidate).orElseThrow(UserNotFoundException::new);
        jobRepository.findById(idJob).orElseThrow(JobNotFoundException::new);

        ApplyJobEntity applyJobEntity = ApplyJobEntity.builder()
                .candidateId(idCandidate)
                .jobId(idJob)
                .build();

        applyJobEntity = applyJobRepository.save(applyJobEntity);
        return applyJobEntity;
    }
}
