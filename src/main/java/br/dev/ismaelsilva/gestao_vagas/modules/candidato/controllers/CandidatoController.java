package br.dev.ismaelsilva.gestao_vagas.modules.candidato.controllers;


import br.dev.ismaelsilva.gestao_vagas.exception.UserAlreadyExists;
import br.dev.ismaelsilva.gestao_vagas.modules.candidato.CandidatoEntity;
import br.dev.ismaelsilva.gestao_vagas.modules.candidato.CandidatoRepository;
import br.dev.ismaelsilva.gestao_vagas.modules.candidato.dto.ProfileCandidatoResponseDto;
import br.dev.ismaelsilva.gestao_vagas.modules.candidato.useCases.CreateCandidatoUseCase;
import br.dev.ismaelsilva.gestao_vagas.modules.candidato.useCases.ProfileCandidatoUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/candidato")
public class CandidatoController {
    @Autowired
    private CreateCandidatoUseCase createCandidatoUseCase;

    @Autowired
    private ProfileCandidatoUseCase profileCandidatoUseCase;
    @PostMapping
    public ResponseEntity<Object> create(@NotNull @Valid @RequestBody CandidatoEntity candidatoEntity){
        try{
            CandidatoEntity result = this.createCandidatoUseCase.execute(candidatoEntity);
            return ResponseEntity.ok().body(result);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    };

    @GetMapping
    public ResponseEntity<Object> get(HttpServletRequest request){
        var idCandidato = request.getAttribute("candidato_id");
        try {
            ProfileCandidatoResponseDto profile =  this.profileCandidatoUseCase.execute(UUID.fromString(idCandidato.toString()));
            return ResponseEntity.ok().body(profile);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
