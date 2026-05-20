package br.com.erickmartins.gestao_vagas.modules.company.controllers.view;

import br.com.erickmartins.gestao_vagas.modules.company.dto.CompanyDTO;
import br.com.erickmartins.gestao_vagas.modules.company.services.CompanyService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/company")
public class CompanyViewController {

    @Autowired
    private JobService jobService;

    @Autowired
    private CompanyService companyService;

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

}
