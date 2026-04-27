package br.com.erickmartins.gestao_vagas.modules.candidate.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "candidate")
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Schema(example = "Junior de Souza",
            requiredMode = Schema.RequiredMode.REQUIRED,
            description = "Nome do candidato")
    private String name;

    @Pattern(regexp = "\\S+", message = "O campo [username] não deve conter espaço.")
    @Schema(example = "junior",
            requiredMode = Schema.RequiredMode.REQUIRED,
            description = "Username do candidato")
    private String username;

    @Email(message = "O campo [email] deve conter um e-mail válido.")
    @Schema(example = "junior@gmail.com",
            requiredMode = Schema.RequiredMode.REQUIRED,
            description = "E-mail do candidato")
    private String email;

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
