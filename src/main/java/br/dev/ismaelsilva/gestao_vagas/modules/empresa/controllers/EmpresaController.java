package br.dev.ismaelsilva.gestao_vagas.modules.empresa.controllers;

import br.dev.ismaelsilva.gestao_vagas.modules.empresa.entities.EmpresaEntity;
import br.dev.ismaelsilva.gestao_vagas.modules.empresa.useCases.CreateEmpresaUseCase;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {
    @Autowired
    private CreateEmpresaUseCase createEmpresaUseCase;

    @PostMapping
    public ResponseEntity<Object> create(@NotNull @Valid @RequestBody EmpresaEntity empresaEntity){
        try{
            EmpresaEntity result = this.createEmpresaUseCase.execute(empresaEntity);
            return ResponseEntity.ok().body(result);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
