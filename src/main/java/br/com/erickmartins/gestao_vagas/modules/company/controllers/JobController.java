package br.com.erickmartins.gestao_vagas.modules.company.controllers;

import br.com.erickmartins.gestao_vagas.modules.company.dto.JobDTO;
import br.com.erickmartins.gestao_vagas.modules.company.services.JobService;
import br.com.erickmartins.gestao_vagas.modules.company.entities.JobEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/company/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping("/")
    @PreAuthorize("hasRole('COMPANY')")
    @Tag(name = "Vagas", description = "Informações das vagas")
    @Operation(summary = "Cadastro de vagas",
            description = "Essa função é responsável por cadastrar as vagas dentro da empresa")
    @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = JobEntity.class))
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> create(@Valid @RequestBody JobDTO jobDTO, HttpServletRequest request) {
        try {
            Object companyId = request.getAttribute("company_id");

            JobEntity jobEntity = JobEntity.builder()
                    .benefits(jobDTO.getBenefits())
                    .companyId(UUID.fromString(companyId.toString()))
                    .description(jobDTO.getDescription())
                    .level(jobDTO.getLevel())
                    .build();

            JobEntity result = this.jobService.execute(jobEntity);

            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
