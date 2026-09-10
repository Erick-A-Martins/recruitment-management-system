package br.com.erickmartins.gestao_vagas.modules.company.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCompanyDTO {

    private UUID id;
    private String name;

}
