package br.com.erickmartins.gestao_vagas.modules.candidate.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "candidate")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Column(nullable = false)
    @Schema(example = "Junior de Souza",
            requiredMode = Schema.RequiredMode.REQUIRED,
            description = "Nome do candidato")
    private String name;

    @NotBlank
    @Column(nullable = false, unique = true)
    @Pattern(regexp = "\\S+", message = "O campo [username] não deve conter espaço.")
    @Schema(example = "junior",
            requiredMode = Schema.RequiredMode.REQUIRED,
            description = "Username do candidato")
    private String username;

    @NotBlank
    @Email(message = "O campo [email] deve conter um e-mail válido.")
    @Column(nullable = false, unique = true)
    @Schema(example = "junior@gmail.com",
            requiredMode = Schema.RequiredMode.REQUIRED,
            description = "E-mail do candidato")
    private String email;

    @NotBlank
    @Column(nullable = false)
    @Length(min = 10, max = 100)
    @Schema(example = "admin@1234",
            minLength = 10,
            maxLength = 100,
            requiredMode = Schema.RequiredMode.REQUIRED,
            description = "Senha do candidato")
    private String password;

    @Schema(example = "Desenvolvedor Java",
            requiredMode = Schema.RequiredMode.REQUIRED,
            description = "Breve descrição do candidato")
    private String description;
    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
