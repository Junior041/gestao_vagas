package br.dev.ismaelsilva.gestao_vagas.modules.candidato.controllers;

import br.dev.ismaelsilva.gestao_vagas.modules.candidato.dto.AuthCandidatoRequestDto;
import br.dev.ismaelsilva.gestao_vagas.modules.candidato.dto.AuthCandidatoResponseDto;
import br.dev.ismaelsilva.gestao_vagas.modules.candidato.useCases.AuthCandidatoUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthCandidatoController {
    @Autowired
    private AuthCandidatoUseCase authCandidatoUseCase;
    @PostMapping("/candidato")
    public ResponseEntity<Object> auth(@RequestBody AuthCandidatoRequestDto authCandidatoRequestDto){
        try {
            AuthCandidatoResponseDto token = this.authCandidatoUseCase.execute(authCandidatoRequestDto);
            return ResponseEntity.ok().body(token);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}
