package br.com.erickmartins.gestao_vagas.modules.candidate.controllers;

import br.com.erickmartins.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.erickmartins.gestao_vagas.modules.candidate.entities.CandidateEntity;
import br.com.erickmartins.gestao_vagas.modules.candidate.services.CandidateService;
import br.com.erickmartins.gestao_vagas.modules.candidate.services.ProfileCandidateService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateService createCandidateUseCase;

    @Autowired
    private ProfileCandidateService profileCandidateService;

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
}
