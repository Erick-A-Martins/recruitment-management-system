package br.com.erickmartins.gestao_vagas.modules.candidate.services;

import br.com.erickmartins.gestao_vagas.exceptions.JobNotFoundException;
import br.com.erickmartins.gestao_vagas.exceptions.UserNotFoundException;
import br.com.erickmartins.gestao_vagas.modules.candidate.entities.ApplyJobEntity;
import br.com.erickmartins.gestao_vagas.modules.candidate.entities.CandidateEntity;
import br.com.erickmartins.gestao_vagas.modules.candidate.repositories.ApplyJobRepository;
import br.com.erickmartins.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import br.com.erickmartins.gestao_vagas.modules.candidate.services.ApplyJobCandidateService;
import br.com.erickmartins.gestao_vagas.modules.company.entities.JobEntity;
import br.com.erickmartins.gestao_vagas.modules.company.repositories.JobRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateServiceTest {

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private ApplyJobRepository applyJobRepository;

    @InjectMocks
    private ApplyJobCandidateService applyJobCandidateService;

    @Test
    @DisplayName("Should not be able to apply job with candidate not found")
    void shouldNotBeAbleToApplyJobWithCandidateNotFound() {
        try {
            applyJobCandidateService.execute(null, null);
        } catch (Exception ex) {
            assertThat(ex).isInstanceOf(UserNotFoundException.class);
        }
    }

    @Test
    @DisplayName("Should not be able to apply job with job not found")
    void shouldNotBeAbleToApplyJobWithJobNotFound() {
        UUID candidateId = UUID.randomUUID();
        CandidateEntity candidate = new CandidateEntity();
        when(candidateRepository.findById(candidateId)).thenReturn(Optional.of(candidate));

        try {
            applyJobCandidateService.execute(candidateId, null);
        } catch (Exception ex) {
            assertThat(ex).isInstanceOf(JobNotFoundException.class);
        }
    }

    @Test
    @DisplayName("Should be able to create a new apply job")
    void shouldBeAbleToCreateANewApplyJob() {
        UUID candidateId = UUID.randomUUID();
        UUID jobId = UUID.randomUUID();

        ApplyJobEntity applyJob = ApplyJobEntity.builder()
                .candidateId(candidateId)
                .jobId(jobId)
                .build();

        ApplyJobEntity applyJobEntityCreated = ApplyJobEntity.builder().id(UUID.randomUUID()).build();

        when(candidateRepository.findById(candidateId)).thenReturn(Optional.of(new CandidateEntity()));
        when(jobRepository.findById(jobId)).thenReturn(Optional.of(new JobEntity()));
        when(applyJobRepository.save(applyJob)).thenReturn(applyJobEntityCreated);

        ApplyJobEntity result = applyJobCandidateService.execute(candidateId, jobId);

        assertThat(result).hasFieldOrProperty("id");
        Assertions.assertNotNull(result.getId());
    }
}
