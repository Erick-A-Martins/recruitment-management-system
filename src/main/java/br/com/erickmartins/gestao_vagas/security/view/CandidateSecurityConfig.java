package br.com.erickmartins.gestao_vagas.security.view;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Order(2)
public class CandidateSecurityConfig {

    @Bean

    SecurityFilterChain candidateSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/candidate/**")
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/candidate/login",
                                "/candidate/signIn",
                                "/candidate/create",
                                "/images/**"
                        ).permitAll()
                        .anyRequest().hasRole("CANDIDATE"))
                .formLogin(form -> form
                        .loginPage("/candidate/login")
                        .loginProcessingUrl("/candidate/signIn")
                        .defaultSuccessUrl("/candidate/profile", true)
                        .failureUrl("/candidate/login?error=true")
                );
        return http.build();
    }

}
