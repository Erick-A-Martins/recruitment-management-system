package br.com.erickmartins.gestao_vagas.modules.candidate.services;

import br.com.erickmartins.gestao_vagas.modules.candidate.entities.CandidateEntity;
import br.com.erickmartins.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateUserDetailsService implements UserDetailsService {

    @Autowired
    private CandidateRepository candidateRepository;

    @NonNull
    @Override
    public UserDetails loadUserByUsername(@NonNull String username) {
        CandidateEntity candidate = candidateRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return new User(
                candidate.getUsername(),
                candidate.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_CANDIDATE"))
        );
    }

}
