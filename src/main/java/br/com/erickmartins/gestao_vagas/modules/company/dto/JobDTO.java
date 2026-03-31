package br.com.erickmartins.gestao_vagas.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class JobDTO {

    @Schema(example = "Vaga para pessoa desenvolvedora Júnior", requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;

    @Schema(example = "GYMPass, Plano de sáude", requiredMode = Schema.RequiredMode.REQUIRED)
    private String benefits;

    @Schema(example = "JUNIOR", requiredMode = Schema.RequiredMode.REQUIRED)
    private String level;
}
