package br.com.erickmartins.gestao_vagas.modules.company.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String name;

    @NotBlank(message = "Username é obrigatório")
    private String username;

    @NotBlank(message = "E-mail é obrigatório")
    @Email(message = "E-mail inválido")
    private String email;

    private String website;

    private String description;

    @NotBlank(message = "Senha é obrigatório")
    @Length(min = 10, max = 100, message = "A senha deve ter no mínimo 10 caracteres")
    private String password;

    @NotBlank(message = "Confirme sua senha")
    private String confirmPassword;

}
