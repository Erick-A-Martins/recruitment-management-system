package br.com.erickmartins.gestao_vagas.modules.candidate.controllers.view;

import br.com.erickmartins.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.erickmartins.gestao_vagas.modules.candidate.services.ProfileCandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/candidate")
public class CandidateLoginController {

    @Autowired
    private ProfileCandidateService profileCandidateService;

    @GetMapping("/login")
    public String home() {
        return "candidate/login";
    }

    @GetMapping("/profile")
    @PreAuthorize("hasRole('CANDIDATE')")
    public String profile(Model model) {
        ProfileCandidateResponseDTO candidate = profileCandidateService.getCandidateDetails();
        model.addAttribute("candidate", candidate);
        return "candidate/profile";
    }
}
