package br.dev.ismaelsilva.gestao_vagas.modules.empresa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthEmpresaResponseDto {
    private String access_token;
    private Long expires_in;
}
