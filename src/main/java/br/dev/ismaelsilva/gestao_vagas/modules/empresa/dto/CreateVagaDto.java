package br.dev.ismaelsilva.gestao_vagas.modules.empresa.dto;

import lombok.Data;

@Data
public class CreateVagaDto {
    private String description;
    private String benefits;
    private String level;
}
