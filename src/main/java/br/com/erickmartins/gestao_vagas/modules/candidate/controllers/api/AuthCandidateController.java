package br.com.erickmartins.gestao_vagas.modules.candidate.controllers.api;

import br.com.erickmartins.gestao_vagas.modules.candidate.dto.AuthCandidateRequestDTO;
import br.com.erickmartins.gestao_vagas.modules.candidate.dto.AuthCandidateResponseDTO;
import br.com.erickmartins.gestao_vagas.modules.candidate.services.AuthCandidateService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class AuthCandidateController {

    @Autowired
    private AuthCandidateService authCandidateService;

    @PostMapping("/auth")
    @Tag(name = "Autenticações", description = "Autenticação para candidatos e empresas")
    public ResponseEntity<Object> auth(@RequestBody AuthCandidateRequestDTO authCandidateRequestDTO) {
        try {
            AuthCandidateResponseDTO token = authCandidateService.execute(authCandidateRequestDTO);
            return ResponseEntity.ok().body(token);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
        }
    }
}
