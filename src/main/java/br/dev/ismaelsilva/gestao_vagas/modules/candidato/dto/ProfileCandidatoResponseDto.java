package br.dev.ismaelsilva.gestao_vagas.modules.candidato.dto;

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
    private String description;
    private String username;
    private String email;
    private UUID id;
    private String name;
}
