package br.com.erickmartins.gestao_vagas.modules.company.controllers.view;

import br.com.erickmartins.gestao_vagas.modules.job.dto.JobDTO;
import br.com.erickmartins.gestao_vagas.modules.job.services.JobService;
import br.com.erickmartins.gestao_vagas.security.details.CompanyDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/company")
public class CompanyViewController {

    @Autowired
    private JobService jobService;

    @GetMapping("/jobs")
    @PreAuthorize("hasRole('COMPANY')")
    public String jobs(Model model) {
        model.addAttribute("jobs", new JobDTO());
        return "company/jobs";
    }

    @PostMapping("/jobs")
    @PreAuthorize("HasRole('COMPANY')")
    public String createJob(
            JobDTO jobDTO,
            @AuthenticationPrincipal CompanyDetails companyDetails
    ) {
        jobService.createForView(jobDTO, companyDetails.getCompanyId());
        return "redirect:/company/jobs";
    }
}
