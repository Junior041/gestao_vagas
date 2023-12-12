package br.dev.ismaelsilva.gestao_vagas.modules.empresa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthEmpresaDto {
    private String username;
    private String password;
}
