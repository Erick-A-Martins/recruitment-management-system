package br.com.erickmartins.gestao_vagas.modules.company.controllers.view;

import br.com.erickmartins.gestao_vagas.modules.candidate.dto.ProfileCandidateDTO;
import br.com.erickmartins.gestao_vagas.modules.candidate.mapper.CandidateMapper;
import br.com.erickmartins.gestao_vagas.modules.candidate.services.CandidateService;
import br.com.erickmartins.gestao_vagas.modules.company.dto.CompanyDTO;
import br.com.erickmartins.gestao_vagas.modules.company.dto.ProfileCompanyDTO;
import br.com.erickmartins.gestao_vagas.modules.company.services.CompanyService;
import br.com.erickmartins.gestao_vagas.modules.company.services.ProfileCompanyService;
import br.com.erickmartins.gestao_vagas.modules.job.dto.JobDTO;
import br.com.erickmartins.gestao_vagas.modules.job.services.JobService;
import br.com.erickmartins.gestao_vagas.security.details.CompanyDetails;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/company")
public class CompanyViewController {

    @Autowired
    private JobService jobService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private ProfileCompanyService profileCompanyService;

    @GetMapping("/jobs")
    @PreAuthorize("hasRole('COMPANY')")
    public String jobs(Model model) {
        model.addAttribute("jobs", new JobDTO());
        return "company/jobs";
    }

    @PostMapping("/jobs")
    @PreAuthorize("hasRole('COMPANY')")
    public String createJob(
            JobDTO jobDTO,
            @AuthenticationPrincipal CompanyDetails companyDetails
    ) {
        jobService.createForView(jobDTO, companyDetails.getCompanyId());
        return "redirect:/company/jobs/list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("company", new CompanyDTO());
        return "company/create";
    }

    @PostMapping("create")
    public String save(
            @Valid @ModelAttribute("company") CompanyDTO companyDTO,
            BindingResult result,
            Model model
    ) {

        if (result.hasErrors()) {
            return "company/create";
        }

        if (!companyDTO.getPassword().equals(companyDTO.getConfirmPassword())) {
            model.addAttribute("errorMessage", "As senhas não coincidem");
            return "company/create";
        }

        try {
            // Tem uma treta aqui de salvar e voltar msg de erro no form
            companyService.execute(companyDTO);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", "Erro interno. Tente novamente.");
            return "company/create";
        }

        return "redirect:company/login";
    }

    @GetMapping("/login")
    public String login() {
        return "company/login";
    }

    @GetMapping("/jobs/list")
    @PreAuthorize("hasRole('COMPANY')")
    public String listJobs(Model model) {

        ProfileCompanyDTO companyDTO = profileCompanyService.getCompanyDetails();

        List<JobDTO> jobs = jobService.listAllJobsByCompany(companyDTO.getId());

        model.addAttribute("jobs", jobs);

        return "company/list";
    }

    @GetMapping("/jobs/list/details")
    @PreAuthorize("hasRole('COMPANY')")
    public String jobDetails(@RequestParam("jobId") UUID jobId, Model model) {

        JobDTO job = jobService.getJobByCompany(jobId);

        List<ProfileCandidateDTO> candidates = candidateService.getCandidatesByJob(jobId)
                .stream()
                .map(CandidateMapper::toDTO)
                .toList();

        model.addAttribute("job", job);
        model.addAttribute("candidates", candidates);

        return "company/details";
    }

    @PostMapping("/jobs/list/delete")
    @PreAuthorize("hasRole('COMPANY')")
    public String deleteJob(@RequestParam("jobId") UUID jobId) {

        jobService.deleteJob(jobId);

        return "redirect:/company/jobs/list";
    }

}
