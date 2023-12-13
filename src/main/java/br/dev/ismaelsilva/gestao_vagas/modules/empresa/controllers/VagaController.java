package br.dev.ismaelsilva.gestao_vagas.modules.empresa.controllers;

import br.dev.ismaelsilva.gestao_vagas.modules.empresa.dto.CreateVagaDto;
import br.dev.ismaelsilva.gestao_vagas.modules.empresa.entities.VagaEntity;
import br.dev.ismaelsilva.gestao_vagas.modules.empresa.useCases.CreateVagaUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/empresa/vaga")
public class VagaController {

    @Autowired
    private CreateVagaUseCase createVagaUseCase;

    @PostMapping
    @PreAuthorize("hasRole('EMPRESA')")
    public VagaEntity create(@Valid @RequestBody CreateVagaDto createVagaDto, HttpServletRequest request){
        System.out.println(request.getAttribute("empresa_id").toString());
        VagaEntity vagaEntity = VagaEntity.builder()
                .benefits(createVagaDto.getBenefits())
                .description(createVagaDto.getDescription())
                .level(createVagaDto.getLevel())
                .empresaId(UUID.fromString(request.getAttribute("empresa_id").toString()))
                .build();

        return this.createVagaUseCase.execute(vagaEntity);
    }
}
