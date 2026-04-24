package br.com.erickmartins.gestao_vagas.modules.candidate.controllers.view;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/candidate")
public class CandidateLoginController {

    @GetMapping("/login")
    public String home() {
        return "candidate/login";
    }

    @GetMapping("/profile")
    @PreAuthorize("hasRole('CANDIDATE')")
    public String profile() {
        return "candidate/profile";
    }
}
