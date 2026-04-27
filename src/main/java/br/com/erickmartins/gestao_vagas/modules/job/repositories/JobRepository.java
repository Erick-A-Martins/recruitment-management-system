package br.com.erickmartins.gestao_vagas.modules.job.repositories;

import br.com.erickmartins.gestao_vagas.modules.job.entities.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {

    List<JobEntity> findByDescriptionContainingIgnoreCase(String filter);

    @Query("""
            SELECT j
            FROM JobEntity j
            WHERE (:filter IS NULL
                        OR LOWER(j.description) LIKE LOWER(CONCAT('%', :filter, '%')))
            AND j.id NOT IN (
            	SELECT aj.jobId
                FROM ApplyJobEntity aj
            	WHERE aj.candidateId = :candidateId
            )
            """)
    List<JobEntity> findAvailableJobs(
            @Param("candidateId") UUID candidateId,
            @Param("filter") String filter
    );
}
