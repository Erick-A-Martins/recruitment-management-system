package br.com.erickmartins.gestao_vagas.modules.candidate.services;

import br.com.erickmartins.gestao_vagas.exceptions.UserFoundException;
import br.com.erickmartins.gestao_vagas.modules.candidate.entities.CandidateEntity;
import br.com.erickmartins.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateEntity execute(CandidateEntity candidateEntity) {
        this.candidateRepository
                .findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });

        return this.candidateRepository.save(candidateEntity);
    }
}
