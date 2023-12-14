package br.dev.ismaelsilva.gestao_vagas.modules.candidato.controllers;


import br.dev.ismaelsilva.gestao_vagas.modules.candidato.entities.CandidatoEntity;
import br.dev.ismaelsilva.gestao_vagas.modules.candidato.dto.ProfileCandidatoResponseDto;
import br.dev.ismaelsilva.gestao_vagas.modules.candidato.useCases.CreateCandidatoUseCase;
import br.dev.ismaelsilva.gestao_vagas.modules.candidato.useCases.ListAllVagasByFilterUseCase;
import br.dev.ismaelsilva.gestao_vagas.modules.candidato.useCases.ProfileCandidatoUseCase;
import br.dev.ismaelsilva.gestao_vagas.modules.empresa.entities.VagaEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/candidato")
@Tag(name = "Candidato", description = "Informações do candidato")
public class CandidatoController {
    @Autowired
    private CreateCandidatoUseCase createCandidatoUseCase;

    @Autowired
    private ListAllVagasByFilterUseCase listAllVagasByFilterUseCase;

    @Autowired
    private ProfileCandidatoUseCase profileCandidatoUseCase;
    @PostMapping
    @Operation(
            summary = "Cadastro de candidato",
            description = "Essa função é responsável por cadastrar um candidato"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema =  @Schema(implementation = CandidatoEntity.class))
            }),
            @ApiResponse(responseCode = "400", description = "Usuário já existe")
    })
    public ResponseEntity<Object> create(@NotNull @Valid @RequestBody CandidatoEntity candidatoEntity){
        try{
            CandidatoEntity result = this.createCandidatoUseCase.execute(candidatoEntity);
            return ResponseEntity.ok().body(result);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    };

    @GetMapping
    @PreAuthorize("hasRole('CANDIDATO')")
    @Operation(
            summary = "Perfil do candidato",
            description = "Essa função é responsável por buscar as informações do perfil do candidato"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema =  @Schema(implementation = ProfileCandidatoResponseDto.class))
            }),
            @ApiResponse(responseCode = "400", description = "Usuário não encontrado")
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<Object> get(@NotNull HttpServletRequest request){
        var idCandidato = request.getAttribute("candidato_id");
        try {
            ProfileCandidatoResponseDto profile =  this.profileCandidatoUseCase.execute(UUID.fromString(idCandidato.toString()));
            return ResponseEntity.ok().body(profile);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/vagas")
    @PreAuthorize("hasRole('CANDIDATO')")
    @Operation(
            summary = "Listagem de vagas disponíveis para o candidato",
            description = "Essa função é responsável por listar todas as vagas com base no filtro"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = VagaEntity.class)))
            })
    })
    @SecurityRequirement(name = "jwt_auth")
    public List<VagaEntity> findJobByFilter(@RequestParam String filter){
        return this.listAllVagasByFilterUseCase.execute(filter);
    }
}
