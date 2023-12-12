package br.dev.ismaelsilva.gestao_vagas.modules.candidato.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthCandidatoResponseDto {
    private String access_token;
    private Long expires_in;
}
