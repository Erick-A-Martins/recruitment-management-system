package br.com.erickmartins.gestao_vagas.security.details;

import br.com.erickmartins.gestao_vagas.modules.candidate.entities.CandidateEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class CandidateDetails implements UserDetails {

    private final CandidateEntity candidate;

    public UUID getCandidateId() {
        return candidate.getId();
    }

    @NonNull
    @Override
    public String getUsername() {
        return candidate.getUsername();
    }

    @NonNull
    @Override
    public String getPassword() {
        return candidate.getPassword();
    }

    @NonNull
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_CANDIDATE"));
    }
}
