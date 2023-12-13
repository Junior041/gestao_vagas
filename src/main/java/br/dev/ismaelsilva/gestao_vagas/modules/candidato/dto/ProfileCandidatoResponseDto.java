package br.dev.ismaelsilva.gestao_vagas.modules.candidato.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCandidatoResponseDto {

    @Schema(example = "Desenvolvedora Java")
    private String description;

    @Schema(example = "Maria")
    private String username;

    @Schema(example = "Maria@gmail.com")
    private String email;

    private UUID id;

    @Schema(example = "Maria da Silva")
    private String name;
}
