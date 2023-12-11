package br.dev.ismaelsilva.gestao_vagas.modules.candidato.controllers;


import br.dev.ismaelsilva.gestao_vagas.exception.UserAlreadyExists;
import br.dev.ismaelsilva.gestao_vagas.modules.candidato.CandidatoEntity;
import br.dev.ismaelsilva.gestao_vagas.modules.candidato.CandidatoRepository;
import br.dev.ismaelsilva.gestao_vagas.modules.candidato.useCases.CreateCandidatoUseCase;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidato")
public class CandidatoController {
    @Autowired
    private CreateCandidatoUseCase createCandidatoUseCase;
    @PostMapping
    public ResponseEntity<Object> create(@NotNull @Valid @RequestBody CandidatoEntity candidatoEntity){
        try{
            CandidatoEntity result = this.createCandidatoUseCase.execute(candidatoEntity);
            return ResponseEntity.ok().body(result);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    };

}
