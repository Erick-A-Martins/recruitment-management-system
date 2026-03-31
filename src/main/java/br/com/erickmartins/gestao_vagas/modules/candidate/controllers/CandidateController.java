package br.com.erickmartins.gestao_vagas.modules.candidate.controllers;

import br.com.erickmartins.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.erickmartins.gestao_vagas.modules.candidate.entities.CandidateEntity;
import br.com.erickmartins.gestao_vagas.modules.candidate.services.CandidateService;
import br.com.erickmartins.gestao_vagas.modules.candidate.services.ListAllJobsByFilterService;
import br.com.erickmartins.gestao_vagas.modules.candidate.services.ProfileCandidateService;
import br.com.erickmartins.gestao_vagas.modules.company.entities.JobEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateService createCandidateUseCase;

    @Autowired
    private ProfileCandidateService profileCandidateService;

    @Autowired
    private ListAllJobsByFilterService listAllJobsByFilterService;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
        try {
            CandidateEntity result = createCandidateUseCase.execute(candidateEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<Object> get(HttpServletRequest request) {

        var candidateId = request.getAttribute("candidate_id");

        try {
            ProfileCandidateResponseDTO profile = profileCandidateService.execute(UUID.fromString(candidateId.toString()));
            return ResponseEntity.ok().body(profile);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/job/")
    @PreAuthorize("hasRole('CANDIDATE')")
    public List<JobEntity> findJobByFilter(@RequestParam String filter) {
        return listAllJobsByFilterService.execute(filter);
    }
}
