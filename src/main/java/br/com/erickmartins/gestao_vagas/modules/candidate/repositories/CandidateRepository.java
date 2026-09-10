package br.com.erickmartins.gestao_vagas.modules.candidate.repositories;


import br.com.erickmartins.gestao_vagas.modules.candidate.entities.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {
    Optional<CandidateEntity> findByUsernameOrEmail(String username, String email);

    Optional<CandidateEntity> findByUsername(String username);

    @Query("""
            SELECT c
            FROM CandidateEntity c
            JOIN ApplyJobEntity aj ON aj.candidateId = c.id
            WHERE aj.jobId = :jobId
            """)
    List<CandidateEntity> findCandidatesByJobId(@Param("jobId") UUID jobId);
}
