package br.com.erickmartins.gestao_vagas.modules.job.repositories;

import br.com.erickmartins.gestao_vagas.modules.job.entities.ApplyJobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApplyJobRepository extends JpaRepository<ApplyJobEntity, UUID> {
}
