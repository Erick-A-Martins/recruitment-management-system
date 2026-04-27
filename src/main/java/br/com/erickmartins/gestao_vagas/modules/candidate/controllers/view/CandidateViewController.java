package br.com.erickmartins.gestao_vagas.modules.candidate.controllers.view;

import br.com.erickmartins.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.erickmartins.gestao_vagas.modules.job.services.ApplyJobCandidateService;
import br.com.erickmartins.gestao_vagas.modules.candidate.services.ListAllJobsByFilterService;
import br.com.erickmartins.gestao_vagas.modules.candidate.services.ProfileCandidateService;
import br.com.erickmartins.gestao_vagas.modules.job.dto.JobDTO;
import br.com.erickmartins.gestao_vagas.modules.job.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/candidate")
public class CandidateViewController {

    @Autowired
    private ProfileCandidateService profileCandidateService;

    @Autowired
    private ListAllJobsByFilterService listAllJobsByFilterService;

    @Autowired
    private JobService jobService;

    @Autowired
    private ApplyJobCandidateService applyJobCandidateService;

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
