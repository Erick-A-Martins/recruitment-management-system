package br.com.erickmartins.gestao_vagas.modules.candidate.controllers.view;

import br.com.erickmartins.gestao_vagas.modules.candidate.dto.CreateCandidateDTO;
import br.com.erickmartins.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.erickmartins.gestao_vagas.modules.candidate.services.CandidateService;
import br.com.erickmartins.gestao_vagas.modules.candidate.services.ProfileCandidateService;
import br.com.erickmartins.gestao_vagas.modules.job.dto.JobDTO;
import br.com.erickmartins.gestao_vagas.modules.job.services.ApplyJobCandidateService;
import br.com.erickmartins.gestao_vagas.modules.job.services.JobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/candidate")
public class CandidateViewController {

    @Autowired
    private ProfileCandidateService profileCandidateService;

    @Autowired
    private JobService jobService;

    @Autowired
    private ApplyJobCandidateService applyJobCandidateService;

    @Autowired
    private CandidateService candidateService;

    @GetMapping("/login")
    public String login() {
        return "candidate/login";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("candidate", new CreateCandidateDTO());
        return "candidate/create";
    }

    @PostMapping("/create")
    public String save(
            @Valid @ModelAttribute("candidate") CreateCandidateDTO candidate,
            BindingResult result,
            Model model
    ) {

        if (result.hasErrors()) {
            return "candidate/create";
        }

        if (!candidate.getPassword().equals(candidate.getConfirmPassword())) {
            model.addAttribute("errorMessage", "As senhas não coincidem");
            return "candidate/create";
        }

        try {
            candidateService.execute(candidate);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", "Erro interno. Tente novamente.");
            return "candidate/create";
        }

        return "redirect:/candidate/login";
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
        ProfileCandidateResponseDTO candidate = profileCandidateService.getCandidateDetails();

        List<JobDTO> jobs = jobService.getAvailableJobs(candidate.getId(), filter);

        model.addAttribute("jobs", jobs);

        System.out.println(jobs);

        return "candidate/jobs";
    }

    @PostMapping("/jobs/apply")
    @PreAuthorize("hasRole('CANDIDATE')")
    public String applyJob(@RequestParam("jobId") UUID jobId) {
        ProfileCandidateResponseDTO candidate = profileCandidateService.getCandidateDetails();
        applyJobCandidateService.execute(candidate.getId(), jobId);
        return "redirect:/candidate/jobs";
    }
}
