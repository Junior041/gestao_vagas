package br.dev.ismaelsilva.gestao_vagas.modules.empresa.controllers;

import br.dev.ismaelsilva.gestao_vagas.modules.empresa.entities.VagaEntity;
import br.dev.ismaelsilva.gestao_vagas.modules.empresa.useCases.CreateVagaUseCase;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vaga")
public class VagaController {

    @Autowired
    private CreateVagaUseCase createVagaUseCase;

    @PostMapping
    public ResponseEntity<Object> create(@NotNull @Valid @RequestBody VagaEntity vagaEntity){
        try {
            VagaEntity result = this.createVagaUseCase.execute(vagaEntity);
            return ResponseEntity.ok().body(result);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
