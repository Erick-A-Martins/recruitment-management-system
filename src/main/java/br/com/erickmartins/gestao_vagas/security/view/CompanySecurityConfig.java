package br.com.erickmartins.gestao_vagas.security.view;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Order(3)
public class CompanySecurityConfig {

    @Bean
    public SecurityFilterChain companySecurityFilterChain(HttpSecurity http) throws Exception {

        http
                .securityMatcher("/company/**")
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/company/login",
                                "/company/signIn",
                                "/company/create",
                                "/images/**"
                        ).permitAll()
                        .anyRequest().hasRole("COMPANY"))
                .formLogin(form -> form
                        .loginPage("/company/login")
                        .loginProcessingUrl("/company/signIn")
                        .defaultSuccessUrl("/company/jobs", true)
                        .failureUrl("/company/login?error=true")
                );

        return http.build();
    }

}
