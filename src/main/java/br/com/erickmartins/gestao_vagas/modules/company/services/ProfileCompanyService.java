package br.com.erickmartins.gestao_vagas.modules.company.services;

import br.com.erickmartins.gestao_vagas.exceptions.UserNotFoundException;
import br.com.erickmartins.gestao_vagas.modules.company.dto.ProfileCompanyDTO;
import br.com.erickmartins.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.erickmartins.gestao_vagas.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ProfileCompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public ProfileCompanyDTO getCompanyDetails() {
        String username = Objects.requireNonNull(SecurityContextHolder
                        .getContext()
                        .getAuthentication())
                        .getName();

        CompanyEntity company = companyRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);

        return ProfileCompanyDTO.builder()
                .id(company.getId())
                .name(company.getName())
                .build();
    }

}
