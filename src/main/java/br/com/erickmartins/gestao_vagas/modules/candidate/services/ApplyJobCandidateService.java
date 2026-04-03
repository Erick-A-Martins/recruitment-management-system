package br.com.erickmartins.gestao_vagas.modules.candidate.services;

import br.com.erickmartins.gestao_vagas.exceptions.JobNotFoundException;
import br.com.erickmartins.gestao_vagas.exceptions.UserNotFoundException;
import br.com.erickmartins.gestao_vagas.modules.candidate.entities.ApplyJobEntity;
import br.com.erickmartins.gestao_vagas.modules.candidate.repositories.ApplyJobRepository;
import br.com.erickmartins.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import br.com.erickmartins.gestao_vagas.modules.company.repositories.JobRepository;
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
        // Validar se candidato existe
        candidateRepository.findById(idCandidate).orElseThrow(UserNotFoundException::new);
        // validar se vaga existe
        jobRepository.findById(idJob).orElseThrow(JobNotFoundException::new);

        // candidato se inscrever na vaga
        ApplyJobEntity applyJobEntity = ApplyJobEntity.builder()
                .candidateId(idCandidate)
                .jobId(idJob)
                .build();

        applyJobEntity = applyJobRepository.save(applyJobEntity);
        return applyJobEntity;
    }
}
