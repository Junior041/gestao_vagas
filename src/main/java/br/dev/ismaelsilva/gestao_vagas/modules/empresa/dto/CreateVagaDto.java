package br.dev.ismaelsilva.gestao_vagas.modules.empresa.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateVagaDto {

    @Schema(example = "Vaga para pessoa desenvolvedora Junior", requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;

    @Schema(example = "Gympass, Plano de saúde", requiredMode = Schema.RequiredMode.REQUIRED)
    private String benefits;

    @Schema(example = "Junior", requiredMode = Schema.RequiredMode.REQUIRED)
    private String level;
}
