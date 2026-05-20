package br.com.erickmartins.gestao_vagas.security.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@Order(1)
@EnableMethodSecurity
public class ApiSecurityConfig {

    @Autowired
    private SecurityCandidateFilter securityCandidateFilter;

    @Autowired
    private SecurityCompanyFilter securityCompanyFilter;

    private static final String[] PERMIT_ALL_LIST = {
            "/api/candidate/auth",
            "/api/company/auth",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/actuator/**"
    };

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/api/**")
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(PERMIT_ALL_LIST).permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(securityCandidateFilter, BasicAuthenticationFilter.class)
                .addFilterBefore(securityCompanyFilter, BasicAuthenticationFilter.class);

        return http.build();
    }

}
