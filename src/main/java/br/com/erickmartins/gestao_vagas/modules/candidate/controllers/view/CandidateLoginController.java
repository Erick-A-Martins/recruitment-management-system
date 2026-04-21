package br.com.erickmartins.gestao_vagas.modules.candidate.controllers.view;

import br.com.erickmartins.gestao_vagas.modules.candidate.dto.AuthCandidateRequestDTO;
import br.com.erickmartins.gestao_vagas.modules.candidate.services.AuthCandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/candidate")
public class CandidateLoginController {

    @Autowired
    private AuthCandidateService authCandidateService;

    @GetMapping("/login")
    public String home(Model model) {
        return "candidate/login";
    }

    @PostMapping("/signIn")
    public String signIn(RedirectAttributes redirectAttributes, String username, String password) {

        try {
            AuthCandidateRequestDTO authCandidateRequestDTO = new AuthCandidateRequestDTO(username, password);
            authCandidateService.execute(authCandidateRequestDTO);
            return "candidate/profile";
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("errorMessage", "Usuário ou Senha incorretos");
            return "redirect:/candidate/login";
        }

    }
}
