package br.com.erickmartins.gestao_vagas.modules.candidate.controllers.view;

import br.com.erickmartins.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.erickmartins.gestao_vagas.modules.candidate.services.ListAllJobsByFilterService;
import br.com.erickmartins.gestao_vagas.modules.candidate.services.ProfileCandidateService;
import br.com.erickmartins.gestao_vagas.modules.job.dto.JobDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/candidate")
public class CandidateViewController {

    @Autowired
    private ProfileCandidateService profileCandidateService;

    @Autowired
    private ListAllJobsByFilterService listAllJobsByFilterService;

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

    @GetMapping("/jobs")
    @PreAuthorize("hasRole('CANDIDATE')")
    public String jobs(Model model, String filter) {

        if (filter == null) {
            filter = "";
        }

        List<JobDTO> jobs = listAllJobsByFilterService.execute(filter);
        model.addAttribute("jobs", jobs);

        return "candidate/jobs";
    }
}
