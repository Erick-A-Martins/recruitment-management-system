package br.com.erickmartins.gestao_vagas.modules.company.services;

import br.com.erickmartins.gestao_vagas.exceptions.UserFoundException;
import br.com.erickmartins.gestao_vagas.modules.company.dto.CompanyDTO;
import br.com.erickmartins.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.erickmartins.gestao_vagas.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CompanyEntity execute(CompanyEntity companyEntity) {
        this.companyRepository
                .findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });

        String password = passwordEncoder.encode(companyEntity.getPassword());
        companyEntity.setPassword(password);

        return this.companyRepository.save(companyEntity);
    }

    public void execute(CompanyDTO companyDTO) {
        companyRepository.findByUsernameOrEmail(companyDTO.getUsername(), companyDTO.getEmail())
                .ifPresent((company) -> {
                    throw new UserFoundException();
                });

        String password = passwordEncoder.encode(companyDTO.getPassword());
        companyDTO.setPassword(password);

        CompanyEntity company = CompanyEntity.builder()
                .username(companyDTO.getUsername())
                .email(companyDTO.getEmail())
                .website(companyDTO.getWebsite())
                .name(companyDTO.getName())
                .password(companyDTO.getPassword())
                .description(companyDTO.getDescription())
                .build();

        companyRepository.save(company);
    }
}
