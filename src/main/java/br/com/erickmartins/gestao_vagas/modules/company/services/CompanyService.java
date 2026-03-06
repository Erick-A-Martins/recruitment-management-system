package br.com.erickmartins.gestao_vagas.modules.company.services;

import br.com.erickmartins.gestao_vagas.exceptions.UserFoundException;
import br.com.erickmartins.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.erickmartins.gestao_vagas.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public CompanyEntity execute(CompanyEntity companyEntity) {
        this.companyRepository
                .findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });

        return this.companyRepository.save(companyEntity);
    }
}
