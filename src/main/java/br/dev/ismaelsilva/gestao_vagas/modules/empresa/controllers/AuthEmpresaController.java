package br.dev.ismaelsilva.gestao_vagas.modules.empresa.controllers;

import br.dev.ismaelsilva.gestao_vagas.modules.empresa.dto.AuthEmpresaDto;
import br.dev.ismaelsilva.gestao_vagas.modules.empresa.useCases.AuthEmpresaUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/auth")
public class AuthEmpresaController {

    @Autowired
    AuthEmpresaUseCase authEmpresaUseCase;

    @PostMapping("/empresa")
    public ResponseEntity<Object> create(@RequestBody AuthEmpresaDto authEmpresaDto) throws AuthenticationException {
        try{
            String result = this.authEmpresaUseCase.execute(authEmpresaDto);
            return ResponseEntity.ok().body(result);
        }catch(Exception e){
            return (ResponseEntity<Object>) ResponseEntity.status(HttpStatus.UNAUTHORIZED);
        }
    }

}
