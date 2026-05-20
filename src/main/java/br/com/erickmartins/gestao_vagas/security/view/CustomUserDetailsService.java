package br.com.erickmartins.gestao_vagas.security.view;

import br.com.erickmartins.gestao_vagas.modules.candidate.entities.CandidateEntity;
import br.com.erickmartins.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import br.com.erickmartins.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.erickmartins.gestao_vagas.modules.company.repositories.CompanyRepository;
import br.com.erickmartins.gestao_vagas.security.details.CandidateDetails;
import br.com.erickmartins.gestao_vagas.security.details.CompanyDetails;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @NonNull
    @Override
    public UserDetails loadUserByUsername(@NonNull String username) {

        Optional<CandidateEntity> candidate = candidateRepository.findByUsername(username);

        if (candidate.isPresent()) {
            return new CandidateDetails(candidate.get());
        }

        Optional<CompanyEntity> company = companyRepository.findByUsername(username);

        if (company.isPresent()) {
            return new CompanyDetails(company.get());
        }

        throw new UsernameNotFoundException("Usuário não encontrado");

    }
}
